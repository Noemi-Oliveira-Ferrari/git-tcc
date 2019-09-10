package br.senai.sp.daumhelp.service;

import br.senai.sp.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfissionalService {

    @POST("profissionais")
    Call<Profissional> cadastrarProfissional(@Body Profissional profissional);

}
