package br.net.daumhelp.service;

import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {

    @POST("clientes")
    Call<Cliente> cadastrarCliente(@Body Cliente cliente);


    @PUT("clientes/atualizar/id/{idCliente}")
    Call<Cliente> atualizarCli(@Header ("token") String token, @Path("idCliente")int idCliente, @Body Cliente cliente);

}
