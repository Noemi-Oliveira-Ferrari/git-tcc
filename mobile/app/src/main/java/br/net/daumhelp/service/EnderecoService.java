package br.net.daumhelp.service;

import br.net.daumhelp.model.Endereco;
import br.net.daumhelp.model.EnderecoViaCep;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnderecoService {

    /*@GET("enderecos/cep/{cep}")
    Call<Endereco> buscarEndereco(@Path("cep")String cep);*/


    @GET("https://viacep.com.br/ws/{cep}/json/")
    Call<EnderecoViaCep> buscarEnderecoViaCep(@Path("cep")String cep);

    @POST("enderecos")
    Call<Endereco> cadastrarEndereco(@Body Endereco endereco);

    @PUT("enderecos/atualizar/id/{idEndereco}")
    Call<Endereco> atualizarEndereco(@Header("token") String token, @Path("idEndereco")Long idEndereco, @Body Endereco endereco);


}
