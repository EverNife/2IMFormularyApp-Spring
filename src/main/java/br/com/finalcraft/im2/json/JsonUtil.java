package br.com.finalcraft.im2.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Gson getGson(){
        return gson;
    }

}
