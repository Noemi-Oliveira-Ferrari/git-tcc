package br.net.daumhelp.service;

import br.net.daumhelp.model.Login;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {

    @POST("/profissionais/login")
    Call<Profissional> buscarPro(@Body Profissional profissional);


    @FormUrlEncoded
    @POST("/clientes/login")
    Call<Login> buscarCli(@Field("email") String email ,
                              @Field("senha") String senha);
    
}
