package br.net.daumhelp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.net.daumhelp.R;
import br.net.daumhelp.RespostaOrcamentoFinalActivity;
import br.net.daumhelp.model.Pedido;

public class ListaAdapterRespostaFinal extends ArrayAdapter<Pedido> {

    private Context context;
    private ArrayList<Pedido> lista;
    private String tokenProfissional;
    private Pedido pedido;

    private ImageView ivFotoCliente;
    private TextView tvTexto;
    private Button btnVisualizar;


    public ListaAdapterRespostaFinal(@NonNull Context context, ArrayList<Pedido> lista, String tokenProfissional) {

        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
        //this.pedido = pedido;
        this.tokenProfissional = tokenProfissional;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Pedido listaPedidos = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_lista_resposta_final, null);

        ivFotoCliente = convertView.findViewById(R.id.civ_foto_cli);
        tvTexto = convertView.findViewById(R.id.tv_texto_aceito);
        btnVisualizar = convertView.findViewById(R.id.btn_visualizar);

        String nomeCliente = listaPedidos.getCliente().getNome();

        int statusPedido = listaPedidos.getStatus().getIdStatusPedido();

        String fotoCli = listaPedidos.getCliente().getFoto();
        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoCli).resize(100,100).into(ivFotoCliente);

        if(statusPedido == 3){

            tvTexto.setText(nomeCliente + "\n aceitou o seu orçamento e quer \nque você realize o serviço");

            btnVisualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RespostaOrcamentoFinalActivity.class);
                    intent.putExtra("pedido", listaPedidos);
                    intent.putExtra("tokenProfissional", tokenProfissional);
                    getContext().startActivity(intent);
                }
            });


        }else if(statusPedido == 5){

            tvTexto.setText(nomeCliente + "\nrecusou o seu orçamento");
            btnVisualizar.setText("Ok!");

            btnVisualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RespostaOrcamentoFinalActivity.class);
                    intent.putExtra("pedido", listaPedidos);
                    intent.putExtra("tokenProfissional", tokenProfissional);
                    getContext().startActivity(intent);
                }
            });

        }

        return convertView;

    }

}
