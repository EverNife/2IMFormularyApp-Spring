package br.com.finalcraft.im2.forms.camunda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamundaReadForm {
    private HashMap<String, CamundaVariable> variables;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CamundaVariable{
        Object value;
        String type;
    }

}
