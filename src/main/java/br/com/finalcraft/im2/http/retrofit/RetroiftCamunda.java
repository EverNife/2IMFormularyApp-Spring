package br.com.finalcraft.im2.http.retrofit;

import br.com.finalcraft.im2.forms.camunda.CamundaCreateForm;
import br.com.finalcraft.im2.forms.validation.CreateFormularyResponse;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroiftCamunda {

    @POST("process-definition/key/{key}/start")
    Call<CreateFormularyResponse> createCamundaProcess(@Path("key") String key, @Body CamundaCreateForm camundaCreateForm);

    @GET("process-instance/{id}/variables")
    Call<JsonObject> fetchCamundaProcess(@Path("id") String key);

}
