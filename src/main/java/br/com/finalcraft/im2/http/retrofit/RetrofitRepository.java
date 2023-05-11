package br.com.finalcraft.im2.http.retrofit;


import br.com.finalcraft.im2.config.IM2Settings;
import br.com.finalcraft.im2.http.HttpClient;

public class RetrofitRepository {

    private static RetroiftCamunda retroiftCamunda;

    public static void initialize(){
        retroiftCamunda = HttpClient.getRetrofit(IM2Settings.COMUNDA_ENDPOINT)
                .create(RetroiftCamunda.class);
    }

    public static RetroiftCamunda getCamundaAPI(){
        return retroiftCamunda;
    }

}
