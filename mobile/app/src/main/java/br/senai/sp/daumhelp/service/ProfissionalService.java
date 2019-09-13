package br.senai.sp.daumhelp.service;

import br.senai.sp.daumhelp.model.Confirmacao;
import br.senai.sp.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProfissionalService {

    @POST("profissionais")
    Call<Profissional> cadastrarProfissional(@Body Profissional profissional);

    @POST("profissionais/confirmacao")
    Call<Confirmacao> confirmarEmail(@Body Confirmacao confirmacao);
}
