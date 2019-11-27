package br.net.daumhelp.menuCli.respostaCli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import br.net.daumhelp.R;
import br.net.daumhelp.adapter.ListaAdapterPedidosPendentes;
import br.net.daumhelp.adapter.ListaAdapterRespostaOrcamento;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RespostaCliFragmentActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RespostaCliViewModel respostaCliViewModel;
    private int idStatusOrcado;
    private int idStatusRejeitado;
    private int idCliente;
    private Cliente cliente;
    private String tokenCliente;
    private ListaAdapterRespostaOrcamento listaPedidos;
    private SwipeRefreshLayout mSwipeToRefresh;
    private ListView listView;
    ArrayList<Pedido> lista = new ArrayList<Pedido>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        respostaCliViewModel = ViewModelProviders.of(this).get(RespostaCliViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_resposta_cliente, container, false);
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progressbar);

        listView =  getView().findViewById(R.id.lv_resposta_orcamento);
        mSwipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_resposta_cli);
        mSwipeToRefresh.setOnRefreshListener(this);

        Intent intent = getActivity().getIntent();
        if (intent.getSerializableExtra("tokenCliente") != null) {
            tokenCliente = (String) intent.getSerializableExtra("tokenCliente");
        }

        if(intent.getSerializableExtra("cliente") != null) {
            cliente = (Cliente) intent.getSerializableExtra("cliente");
        }

        idStatusOrcado = 2;
        idStatusRejeitado = 6;
        idCliente = cliente.getIdCliente();
        final ListaAdapterRespostaOrcamento listaPedidos = new ListaAdapterRespostaOrcamento(getContext(), lista, tokenCliente);

        Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosPorCliente(tokenCliente, idCliente, idStatusOrcado, idStatusRejeitado);
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                lista = (ArrayList<Pedido>) response.body();
                if (lista != null) {
                    for (Pedido p : lista) {
                        listaPedidos.add(p);
                    }
                    listView.setAdapter(listaPedidos);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.i("PROFISSIONAIS BUSCA", t.getMessage());
                progressDialog.dismiss();
            }

        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onRefresh() {

        listaPedidos = new ListaAdapterRespostaOrcamento(getContext(), lista, tokenCliente);

       /* Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosPorCliente(tokenCliente, idCliente, idStatusOrcado, idStatusRejeitado);
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                lista = (ArrayList<Pedido>) response.body();
                if (lista != null) {
                    for (Pedido p : lista) {
                        listaPedidos.add(p);
                    }
                    listView.setAdapter(listaPedidos);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.i("PROFISSIONAIS BUSCA", t.getMessage());
            }

        });*/

        Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosPorCliente(tokenCliente, idCliente, idStatusOrcado, idStatusRejeitado);
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                lista = (ArrayList<Pedido>) response.body();

                if(lista != null){

                    listaPedidos = new ListaAdapterRespostaOrcamento(getContext(), lista, tokenCliente);
                    ListView listView = (ListView) getView().findViewById(R.id.lv_resposta_orcamento);
                    listView.setAdapter(listaPedidos);
                    mSwipeToRefresh.setRefreshing(false);

                }else{
                    mSwipeToRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.i("Servico Pendente", t.getMessage());
            }

        });

    }
}

