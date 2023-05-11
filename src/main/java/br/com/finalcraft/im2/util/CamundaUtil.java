package br.com.finalcraft.im2.util;

import br.com.finalcraft.im2.datamodel.data.Formulary;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;

public class CamundaUtil {

    public static void appendVariables(Formulary formulary, String identifier, JsonObject body) throws NoSuchFieldException, IllegalAccessException {
        formulary.setId(identifier);

        for (String fieldName : body.keySet()) {
            Field declaredField = formulary.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            JsonElement value = body.getAsJsonObject(fieldName).get("value");
            String type = body.getAsJsonObject(fieldName).get("type").getAsString();
            switch (type){
                case "Integer":
                    declaredField.set(formulary, value.getAsInt());
                    break;
                case "String":
                    declaredField.set(formulary, value.getAsString());
                    break;
                case "Boolean":
                    declaredField.set(formulary, value.getAsBoolean());
                    break;
            }
        }
    }
}
