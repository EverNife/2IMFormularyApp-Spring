package br.com.finalcraft.im2.springconfig.runners;

import br.com.finalcraft.im2.config.ConfigManager;
import br.com.finalcraft.im2.config.IM2Settings;
import br.com.finalcraft.im2.datamodel.data.Formulary;
import br.com.finalcraft.im2.datamodel.managers.FormularyRepository;
import br.com.finalcraft.im2.forms.camunda.CamundaCreateForm;
import br.com.finalcraft.im2.forms.validation.CreateFormularyForm;
import br.com.finalcraft.im2.forms.validation.CreateFormularyResponse;
import br.com.finalcraft.im2.http.retrofit.RetrofitRepository;
import br.com.finalcraft.im2.json.JsonUtil;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import retrofit2.Response;

import java.util.List;

@Repository
@Component
@Order(1)
public class DatabasePopulateRunner implements ApplicationRunner {

    @Autowired
    FormularyRepository formularyRepository;

    public void run(ApplicationArguments args) {
        ConfigManager.initialize();

        List<Formulary> all = formularyRepository.findAll();

        System.out.println("There is a total of %s formularies in the database.".formatted(all.size()));

        //addTestFormulary(); //Se necessário testar a adição de formulários
    }

    public void addTestFormulary(){
        CreateFormularyForm createFormularyForm = new CreateFormularyForm(
                "Teste",
                "Teste",
                1,
                1,
                1,
                1,
                1,
                1,
                true,
                true,
                "Teste"
        );
        Formulary formulary = new Formulary(createFormularyForm);
        formularyRepository.save(formulary);
//
        //ADd To Camunda
        try {
            CamundaCreateForm camundaCreateForm = CamundaCreateForm.from(createFormularyForm);
            JsonUtil.getGson().toJson(camundaCreateForm, System.out);

            Response<CreateFormularyResponse> execute = RetrofitRepository.getCamundaAPI().createCamundaProcess(IM2Settings.COMUNDA_KEY, camundaCreateForm).execute();

            System.out.println("Success: " + execute.isSuccessful());
            System.out.println("Code: " + execute.code());
            System.out.println("Message: " + execute.message());
            System.out.println("Body: " + execute.body());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
