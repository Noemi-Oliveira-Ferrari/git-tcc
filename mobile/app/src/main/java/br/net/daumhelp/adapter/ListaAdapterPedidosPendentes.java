package br.net.daumhelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import br.net.daumhelp.R;
import br.net.daumhelp.model.Pedido;
public class ListaAdapterPedidosPendentes extends ArrayAdapter<Pedido> {

    private Context context;
    private ArrayList<Pedido> lista;
    private TextView nomeCliente;
    private TextView cidadeCliente;


    public ListaAdapterPedidosPendentes(@NonNull Context context, ArrayList<Pedido> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Pedido pedidosPosicao = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_lista_pedidos_pendentes, null);


        nomeCliente = convertView.findViewById(R.id.tv_nome_cliente);
        cidadeCliente = convertView.findViewById(R.id.tv_cidade_cliente);


        return convertView;



    }
}
