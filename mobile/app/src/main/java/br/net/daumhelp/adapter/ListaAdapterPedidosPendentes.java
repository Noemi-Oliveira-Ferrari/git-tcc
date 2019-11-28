package br.net.daumhelp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.net.daumhelp.MainActivity;
import br.net.daumhelp.MenuActivity;
import br.net.daumhelp.R;
import br.net.daumhelp.VisualizarDetalhesServicoActivity;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.menu.home.PedidosFragmentActivity;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.recursos.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAdapterPedidosPendentes extends ArrayAdapter<Pedido> {

    private Context context;
    private ArrayList<Pedido> lista;
    private TextView tvNomeCliente;
    private TextView tvCidadeCliente;
    private TextView tvDataSolicitacao;
    private Button btnRecusar;
    private Button btnAnalisar;
    private ListView listView;
    private ImageView ivFotoCliente;
    private String tokenProfissional;


    public ListaAdapterPedidosPendentes(@NonNull Context context, ArrayList<Pedido> lista, String tokenProfissional) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
        this.tokenProfissional = tokenProfissional;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Pedido listaPedidos = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_lista_pedidos_pendentes, null);


        tvNomeCliente = convertView.findViewById(R.id.tv_nome_cliente);
        tvCidadeCliente = convertView.findViewById(R.id.tv_cidade_cliente);
        tvDataSolicitacao = convertView.findViewById(R.id.tv_solicitacao_data);
        btnRecusar = convertView.findViewById(R.id.ib_recusar);
        btnAnalisar = convertView.findViewById(R.id.ib_analisar);
        ivFotoCliente = convertView.findViewById(R.id.profile_image);

        tvNomeCliente.setText(listaPedidos.getCliente().getNome().toUpperCase());
        tvCidadeCliente.setText(listaPedidos.getCliente().getEndereco().getCidade().getCidade() + ", " + listaPedidos.getCliente().getEndereco().getCidade().getMicrorregiao().getUf().getUf());


        Date data = Data.stringToDate(listaPedidos.getDataHora());
        String dataFormatada = Data.dataHoraToBrString(data);
        tvDataSolicitacao.setText(dataFormatada);


        String fotoCli = listaPedidos.getCliente().getFoto();
        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoCli).resize(100,100).rotate(90).into(ivFotoCliente);

        btnAnalisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VisualizarDetalhesServicoActivity.class);
                intent.putExtra("idPedido", listaPedidos.getIdPedido());
                intent.putExtra("tokenProfissional", tokenProfissional);
                getContext().startActivity(intent);
            }
        });

        btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Cancelar pedido do " + listaPedidos.getCliente().getNome().toUpperCase()).setMessage("Ao recusar a solicitação, você não terá acesso a esse pedido novamente. \n Deseja continuar?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                        int idPedido = listaPedidos.getIdPedido();

                        Call<Pedido> call = new RetroFitConfig().getPedidoService().recusarPedidoPendente(tokenProfissional,idPedido);
                        call.enqueue(new Callback<Pedido>() {
                            @Override
                            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                                response.body();
                                Toast.makeText(getContext(), "Pedido recusado =(", Toast.LENGTH_SHORT).show();

                                }

                            @Override
                            public void onFailure(Call<Pedido> call, Throwable t) {
                                Log.i("Servico Pendente", t.getMessage());
                            }

                        });


                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });





        return convertView;



    }
}
