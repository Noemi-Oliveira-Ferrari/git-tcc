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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.net.daumhelp.MainActivity;
import br.net.daumhelp.MenuActivity;
import br.net.daumhelp.MenuClienteActivity;
import br.net.daumhelp.R;
import br.net.daumhelp.RespostaOrcamentoActivity;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.menuCli.respostaCli.RespostaCliFragmentActivity;
import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAdapterRespostaOrcamento extends ArrayAdapter<Pedido> {


    private Context context;
    private ArrayList<Pedido> lista;
    private String tokenCliente;
    private Pedido pedido;

    private ImageView ivProfissional;
    private TextView tvNomeProfissional;
    private TextView tvSubcategoriaProfissional;
    private TextView tvTextoOrcamento;
    private Button btnConfirmar;
    private Button btnOk;

    public ListaAdapterRespostaOrcamento(@NonNull Context context, ArrayList<Pedido> lista, String tokenCliente) {

        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
        //this.pedido = pedido;
        this.tokenCliente = tokenCliente;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Pedido listaPedidos = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_lista_orcado, null);

        ivProfissional = convertView.findViewById(R.id.civ_foto_pro);
        tvNomeProfissional = convertView.findViewById(R.id.tv_nome_profissional);
        tvSubcategoriaProfissional = convertView.findViewById(R.id.tv_subcategoria_profissional);
        tvTextoOrcamento = convertView.findViewById(R.id.tv_texto_orcamento);
        btnConfirmar = convertView.findViewById(R.id.btn_confirmar);
        btnOk = convertView.findViewById(R.id.btn_ok);

        tvNomeProfissional.setText(listaPedidos.getProfissional().getNome().toUpperCase());
        tvSubcategoriaProfissional.setText(listaPedidos.getProfissional().getSubcategoria().getSubcategoria());


        String fotoPro = listaPedidos.getProfissional().getFoto();
        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoPro).resize(100,100).into(ivProfissional);

        final int idStatus = listaPedidos.getIdPedido();

        int statusPedido = listaPedidos.getStatus().getIdStatusPedido();

        if(statusPedido == 2){

            tvTextoOrcamento.setText("Seu orçamento está pronto! =)");
            
        }else if(statusPedido == 4){

            tvTextoOrcamento.setText("Infelizmente não estou disponível =(");
            btnConfirmar.setVisibility(View.GONE);
            btnOk.setVisibility(View.VISIBLE);

        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), RespostaOrcamentoActivity.class);
                intent.putExtra("pedido", listaPedidos);
                intent.putExtra("tokenCliente", tokenCliente);
                getContext().startActivity(intent);

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Call<Pedido> call = new RetroFitConfig().getPedidoService().concluirServico(tokenCliente,idStatus);
                call.enqueue(new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {

                        response.body();


                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("Orçamento").setMessage("Esse profissional não está disponível, vamos tirá-lo da sua lista de resposta").setPositiveButton("Certo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        Log.i("Servico Pendente", t.getMessage());
                    }

                });
            }
        });

        return convertView;
    }

}
