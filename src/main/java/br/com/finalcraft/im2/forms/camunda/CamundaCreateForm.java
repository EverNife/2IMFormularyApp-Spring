package br.com.finalcraft.im2.forms.camunda;

import br.com.finalcraft.im2.config.IM2Settings;
import br.com.finalcraft.im2.forms.validation.CreateFormularyForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamundaCreateForm {
    private String businessKey;
    private HashMap<String, CamundaVariable> variables;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CamundaVariable{
        Object value;
        String type;
    }

    public static CamundaCreateForm from(CreateFormularyForm createFormularyForm){
        CamundaCreateForm camundaCreateForm = new CamundaCreateForm(IM2Settings.COMUNDA_BUSINESSKEY, new HashMap());

        for (Field declaredField : createFormularyForm.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                camundaCreateForm.getVariables().put(declaredField.getName(), new CamundaVariable(
                        declaredField.get(createFormularyForm),
                        declaredField.getType().getSimpleName()
                ));
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        return camundaCreateForm;
    }
}
