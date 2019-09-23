package br.net.daumhelp.service;

import br.net.daumhelp.model.Confirmacao;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfissionalService {

    @POST("profissionais")
    Call<Profissional> cadastrarProfissional(@Body Profissional profissional);

    @POST("profissionais/confirmacao")
    Call<Confirmacao> confirmarEmail(@Body Confirmacao confirmacao);
}
