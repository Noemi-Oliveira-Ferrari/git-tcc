package br.net.daumhelp.service;

import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoService {

    @POST("pedidos/solicitar")
    Call<Pedido> solicitarProfissional(@Body Pedido pedido);

}
