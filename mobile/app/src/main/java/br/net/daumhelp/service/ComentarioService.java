package br.net.daumhelp.service;

import br.net.daumhelp.model.Comentario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ComentarioService {

    @POST("")
    Call<Comentario> comentarProfissional(@Header("token") String token, @Body Comentario comentario);

}
