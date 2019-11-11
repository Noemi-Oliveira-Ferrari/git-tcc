package br.net.daumhelp.service;

import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FotoService {

    @Multipart
    @POST("imagens/profissional")
    Call<Profissional> cadastrarFotoPro(@Part("idPro") RequestBody idPro, @Part MultipartBody.Part file);


    @POST("imagens/cliente")
    Call<Cliente> cadastrarFotoCli(@Part("idCliente") RequestBody idCliente, @Part MultipartBody.Part file);

}
