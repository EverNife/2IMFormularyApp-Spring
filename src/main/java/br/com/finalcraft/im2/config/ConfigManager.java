package br.com.finalcraft.im2.config;

import br.com.finalcraft.im2.http.retrofit.RetrofitRepository;

public class ConfigManager {

    public static void initialize(){
        IM2Settings.initialize();
        RetrofitRepository.initialize(); //Define retrofit endpoints
    }

}
