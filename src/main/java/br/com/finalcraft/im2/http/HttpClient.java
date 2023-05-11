package br.com.finalcraft.im2.http;

import br.com.finalcraft.im2.json.JsonUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static final OkHttpClient client = new OkHttpClient();

    public static OkHttpClient getClient(){
        return client;
    }

    public static Retrofit getRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(JsonUtil.getGson()))
                .client(client)
                .build();
    }
}
