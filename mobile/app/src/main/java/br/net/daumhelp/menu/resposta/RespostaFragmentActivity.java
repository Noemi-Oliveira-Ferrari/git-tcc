package br.net.daumhelp.menu.resposta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import br.net.daumhelp.R;
import br.net.daumhelp.adapter.ListaAdapterRespostaOrcamento;
import br.net.daumhelp.adapter.ListaAdapterRespostaFinal;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespostaFragmentActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RespostaViewModel respostaViewModel;
    private int idProfissional;
    private Profissional profissional;
    private String tokenProfissional;
    private ListView listView;
    private int idStatusAceito;
    private SwipeRefreshLayout mSwipeToRefresh;

    private ListaAdapterRespostaFinal listaPedidos;
    ArrayList<Pedido> lista = new ArrayList<Pedido>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        respostaViewModel = ViewModelProviders.of(this).get(RespostaViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_resposta, container, false);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progressbar);

        mSwipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_final);
        mSwipeToRefresh.setOnRefreshListener(this);

        listView =  getView().findViewById(R.id.lv_orcamento_final);

        Intent intent = getActivity().getIntent();
        if (intent.getSerializableExtra("tokenProfissional") != null) {
            tokenProfissional = (String) intent.getSerializableExtra("tokenProfissional");

            if(intent.getSerializableExtra("profissional") != null) {
                profissional = (Profissional) intent.getSerializableExtra("profissional");

                idProfissional = profissional.getIdProfissional();

                idStatusAceito = 3;
                int idStatusPedido = 3;
                int idStatusRejeitado = 5;
                final ListaAdapterRespostaFinal listaPedidos = new ListaAdapterRespostaFinal(getContext(), lista, tokenProfissional);

                Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosFinal(tokenProfissional, idProfissional, idStatusPedido, idStatusAceito, idStatusRejeitado);
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

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRefresh() {

        Call<List<Pedido>> call = new RetroFitConfig().getPedidoService().buscarPedidosPendentes(tokenProfissional, idProfissional, idStatusAceito);
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                lista = (ArrayList<Pedido>) response.body();

                if(lista != null){

                    listaPedidos = new ListaAdapterRespostaFinal(getContext(), lista, tokenProfissional);
                    ListView listView = (ListView) getView().findViewById(R.id.lv_orcamento_final);
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
