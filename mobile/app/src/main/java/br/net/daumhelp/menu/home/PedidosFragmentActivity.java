package br.net.daumhelp.menu.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import br.net.daumhelp.R;
import br.net.daumhelp.adapter.ListaAdapterPedidosPendentes;
import br.net.daumhelp.model.Pedido;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;


public class PedidosFragmentActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private PedidosViewModel pedidosViewModel;
    private ListaAdapterPedidosPendentes listaPedidos;

    private ListView listView;

    ArrayList<Pedido> lista = new ArrayList<Pedido>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_pedidos, container, false);

        return root;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {




        ListaAdapterPedidosPendentes listaPedidos =  new ListaAdapterPedidosPendentes(getContext(), lista);

        ListView listView = (ListView) getView().findViewById(R.id.lv_pedidos_pendentes);
        listView.setAdapter(listaPedidos);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "Lista atualizada", Toast.LENGTH_SHORT).show();
    }
    
}