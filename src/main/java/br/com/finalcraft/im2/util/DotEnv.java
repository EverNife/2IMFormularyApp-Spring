package br.com.finalcraft.im2.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class DotEnv {

    private static Map<String,String> env = null;

    public static Map<String,String> getEnv() {
        if (env == null){
            env = new LinkedHashMap();
            Properties properties = new Properties();
            try (var reader = new FileReader(".env")) {
                properties.load(reader);
                properties.forEach((key, value) -> env.put(String.valueOf(key), String.valueOf(value)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return env;
    }

}
