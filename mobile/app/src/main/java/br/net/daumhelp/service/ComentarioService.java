package br.net.daumhelp.service;

import java.util.List;

import br.net.daumhelp.model.Avaliacao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComentarioService {

    @POST("avaliacoes/avaliar")
    Call<Avaliacao> comentarProfissional(@Header("token") String token, @Body Avaliacao avaliacao);

    @GET("avaliacoes/profissional/id/{idProfissional}")
    Call<List<Avaliacao>> carregarComentarios(@Header("token") String token, @Path("idProfissional") int idProfissional);

}
