package br.net.daumhelp.service;

import java.util.List;

import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface PedidoService {

    @POST("pedidos/solicitar")
    Call<Pedido> solicitarProfissional(@Header("token") String token, @Body Pedido pedido);

    @GET("pedidos/profissional/{idProfissional}/status/{idStatusPedido}")
    Call<List<Pedido>> buscarPedidosPendentes(@Header("token") String token, @Path("idProfissional") int idProfissional, @Path("idStatusPedido") int idStatus);

    @GET("pedidos/cliente/{idCliente}/status/{idStatusOrcado}/{idStatusRejeitado}")
    Call<List<Pedido>> buscarPedidosPorCliente(@Header("token") String token, @Path("idCliente") int idCliente, @Path("idStatusOrcado") int idStatusOrcado, @Path("idStatusRejeitado") int idStatusRejeitado);

    @GET("pedidos/id/{idPedido}")
    Call<Pedido> buscarPedidosPorId(@Header("token") String token, @Path("idPedido") int idPedido);

    @PUT("pedidos/resposta/{idPedido}")
    Call<Pedido> fazerOrcamento(@Header("token") String token, @Path("idPedido") int idPedido, @Body Pedido pedido);





    @PUT("pedidos/rejeitar/{idPedido}")
    Call<Pedido> recusarPedidoPendente(@Header("token") String token, @Path("idPedido") int idPedido);

    @PUT("pedidos/aceitar/{idPedido}")
    Call<Pedido> aceitarOrcamento(@Header("token") String token, @Path("idPedido") int idPedido);






}
