package br.com.finalcraft.im2.controllers;

import br.com.finalcraft.im2.config.IM2Settings;
import br.com.finalcraft.im2.datamodel.data.Formulary;
import br.com.finalcraft.im2.datamodel.managers.FormularyRepository;
import br.com.finalcraft.im2.error.ErrorCode;
import br.com.finalcraft.im2.forms.camunda.CamundaCreateForm;
import br.com.finalcraft.im2.forms.validation.CreateFormularyForm;
import br.com.finalcraft.im2.forms.validation.CreateFormularyResponse;
import br.com.finalcraft.im2.http.retrofit.RetrofitRepository;
import br.com.finalcraft.im2.util.CamundaUtil;
import com.google.gson.JsonObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.util.Collection;

@RestController
@RequestMapping(value = "/formulary")
public class FormularyController {

    @Autowired
    private FormularyRepository formularyRepository;

    @GetMapping(value = "/all")
    @CrossOrigin
    public Collection<Formulary> retrieveAllFormularies() {
        return formularyRepository.findAll();
    }

    @GetMapping(value = "/{formularyID}")
    @CrossOrigin
    public Formulary retrieveSingleFormulary(@PathVariable String formularyID) {

        try {
            Response<JsonObject> execute = RetrofitRepository.getCamundaAPI().fetchCamundaProcess(formularyID).execute();
            if (!execute.isSuccessful()){
                throw ErrorCode.FORMULARY_NOT_FOUND.newError();
            }

            JsonObject body = execute.body();
            Formulary formulary = new Formulary();
            CamundaUtil.appendVariables(formulary, formularyID, body);
            return formulary;
        }catch (Exception e){
            throw ErrorCode.CAMUNDA_GENERIC_ERROR.newError()
                    .withMessage("There was some error on the camunda request!")
                    .withData("error", e.getMessage());
        }


        // Não ficou claro no enunciado se eu deveria resgatar o valor do meu banco de dados ou direto do camunda, fiz os 2;
        // Deixei ativado a extração direto do camunda
//        Formulary formulary = formularyRepository.findById(formularyID).orElse(null);
//
//        if (formulary == null){
//            throw ErrorCode.FORMULARY_NOT_FOUND.newError();
//        }
//
//        return formulary;
    }

    @PostMapping
    @CrossOrigin
    public CreateFormularyResponse createFormulary(@RequestBody @Valid CreateFormularyForm createForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            throw ErrorCode.INVALID_FORMULARY_FORM.newError()
                    .withMessage("There was some error on the formularyForm!")
                    .apply(errorResponse -> {
                        bindingResult.getAllErrors().stream()
                                .forEach(objectError -> {
                                    if (objectError instanceof FieldError){
                                        FieldError fieldError = (FieldError) objectError;
                                        errorResponse.withData(fieldError.getField(), fieldError.getDefaultMessage());
                                    }
                                });
                    });
        }

        try {
            CamundaCreateForm camundaCreateForm = CamundaCreateForm.from(createForm);//Camunda requires some weird variable style, lets convert here
            Response<CreateFormularyResponse> execute = RetrofitRepository.getCamundaAPI().createCamundaProcess(IM2Settings.COMUNDA_KEY, camundaCreateForm).execute();

            Formulary formulary = retrieveSingleFormulary(execute.body().getId()); //After sending it to camunda, we get it from camunda back as well
            //only then we add it to database
            formularyRepository.save(formulary);
            return execute.body();
        }catch (Exception e){
            throw ErrorCode.CAMUNDA_GENERIC_ERROR.newError()
                    .withMessage("There was some error on the camunda request!")
                    .withData("error", e.getMessage());
        }
    }
}
