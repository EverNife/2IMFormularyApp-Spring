package br.com.finalcraft.im2.config;

import br.com.finalcraft.im2.util.DotEnv;

public class IM2Settings {

    public static String COMUNDA_ENDPOINT;
    public static String COMUNDA_KEY;
    public static String COMUNDA_BUSINESSKEY;

    public static void initialize(){
        COMUNDA_ENDPOINT = DotEnv.getEnv().get("CamundaSettings.endpoint");
        COMUNDA_KEY = DotEnv.getEnv().get("CamundaSettings.key");
        COMUNDA_BUSINESSKEY = DotEnv.getEnv().get("CamundaSettings.businessKey");
    }

}
