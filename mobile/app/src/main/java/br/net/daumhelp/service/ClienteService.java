package br.net.daumhelp.service;

import br.net.daumhelp.model.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteService {

    @POST("clientes")
    Call<Cliente> cadastrarCliente(@Body Cliente cliente);

}
