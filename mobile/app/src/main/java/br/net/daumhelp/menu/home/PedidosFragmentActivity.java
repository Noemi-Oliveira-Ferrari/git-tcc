package br.net.daumhelp.menu.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import br.net.daumhelp.R;
import br.net.daumhelp.adapter.ListaAdapterBusca;
import br.net.daumhelp.adapter.ListaAdapterPedidosPendentes;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


public class PedidosFragmentActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private PedidosViewModel pedidosViewModel;
    private ListaAdapterPedidosPendentes listaPedidos;
    private SwipeRefreshLayout mSwipeToRefresh;
    private Profissional profissional;
    private int idProfissional;
    private ListView listView;
    private String tokenProfissional;

    ArrayList<Pedido> lista = new ArrayList<Pedido>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_pedidos, container, false);

        return root;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mSwipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_pedidos);
        mSwipeToRefresh.setOnRefreshListener(this);
        listView =  getView().findViewById(R.id.lv_pedidos_pendentes);



        //final ListView listView = (ListView) getView().findViewById(R.id.lv_pedidos_pendentes);

        Intent intent = getActivity().getIntent();

        if (intent.getSerializableExtra("tokenProfissional") != null) {
            tokenProfissional = (String) intent.getSerializableExtra("tokenProfissional");
        }

        final ListaAdapterPedidosPendentes listaPedidos =  new ListaAdapterPedidosPendentes(getContext(), lista, tokenProfissional);

        if (intent.getSerializableExtra("profissional") != null){
            profissional = (Profissional) intent.getSerializableExtra("profissional");
            idProfissional = profissional.getIdProfissional();

            Log.d("pedidos ---> ", tokenProfissional + " - " + idProfissional);

            Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosPendentes(tokenProfissional, idProfissional, 1);
            call.enqueue(new Callback<List<Pedido>>() {
                @Override
                public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                    lista = (ArrayList<Pedido>) response.body();
                    if(lista != null){
                        for(Pedido p : lista){
                            listaPedidos.add(p);
                        }
                        listView.setAdapter(listaPedidos);
                    }

                }

                @Override
                public void onFailure(Call<List<Pedido>> call, Throwable t) {
                    Log.i("Servico Pendente", t.getMessage());
                }

            });

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRefresh() {

        Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosPendentes(tokenProfissional, idProfissional, 1);
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                lista = (ArrayList<Pedido>) response.body();

                if(lista != null){

                    listaPedidos = new ListaAdapterPedidosPendentes(getContext(), lista, tokenProfissional);
                    ListView listView = (ListView) getView().findViewById(R.id.lv_pedidos_pendentes);
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