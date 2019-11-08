package br.net.daumhelp.service;

import java.util.List;

import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoService {

    @POST("pedidos/solicitar")
    Call<Pedido> solicitarProfissional(@Body Pedido pedido);

    @GET("pedidos/solicitados/profissional/{idProfissional}")
    Call<List<Pedido>> buscarPedidosPendentes(@Path("idProfissional") int idProfissional);

    @PUT("pedidos/rejeitar/{idPedido}")
    Call<Pedido>  recusarPedidoPendente(@Path("idPedido") int idPedido);
}
