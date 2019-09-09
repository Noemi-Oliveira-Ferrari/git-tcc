package br.senai.sp.daumhelp.service;

import java.util.List;

import br.senai.sp.daumhelp.model.Endereco;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EnderecoService {

    @GET("enderecos/cep/{cep}")
    Call<Endereco> buscarEndereco(@Path("cep")String cep);

    @POST("enderecos")
    Call<Endereco> cadastrarEndereco(@Body Endereco endereco);

}
