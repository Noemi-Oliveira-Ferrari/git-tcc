package br.net.daumhelp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespostaOrcamentoActivity extends AppCompatActivity {

    private Button btnAceitar;
    private Button btnRecusar;
    private TextView tvOrcamento;
    private TextView tvNomeProfissional;
    private TextView tvSubcategoriaProfissional;
    private TextView tvBalaoResposta;
    private ImageView ivFotoProfissional;
    private View vResposta;


    private Dialog alertDialog;
    private Button btnCerto;
    private ImageView ivResposta;
    private TextView tvTextoAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta_orcamento);

        btnAceitar = findViewById(R.id.btn_aceitar);
        btnRecusar = findViewById(R.id.btn_recusar);
        tvOrcamento = findViewById(R.id.tv_orcamento);
        tvNomeProfissional = findViewById(R.id.tv_nome_profissional);
        tvSubcategoriaProfissional = findViewById(R.id.tv_subcategoria_profissional);
        tvBalaoResposta = findViewById(R.id.tv_resposta);
        ivFotoProfissional = findViewById(R.id.iv_foto_profissional);
        vResposta = findViewById(R.id.v_resposta);

        alertDialog = new Dialog(this);

        alertDialog.setContentView(R.layout.adapter_resposta);
        btnCerto = alertDialog.findViewById(R.id.btn_alert_resposta);
        tvTextoAlert = alertDialog.findViewById(R.id.tv_texto_reposta);
        ivResposta = alertDialog.findViewById(R.id.iv_imagem_resposta);

        getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        vResposta.setVisibility(View.INVISIBLE);



        Intent intent = getIntent();
        if(intent.getSerializableExtra("pedido") != null){

            Pedido pedidoSelecionado = (Pedido) intent.getSerializableExtra("pedido");
            final int idPedido = pedidoSelecionado.getIdPedido();

            if(intent.getSerializableExtra("tokenCliente") != null){

                final String tokenCliente = (String) intent.getSerializableExtra("tokenCliente");


            Locale ptBr = new Locale("pt", "BR");
            String valorString = NumberFormat.getCurrencyInstance(ptBr).format(pedidoSelecionado.getValorServico());

            tvNomeProfissional.setText(pedidoSelecionado.getProfissional().getNome().toUpperCase());
            tvSubcategoriaProfissional.setText(pedidoSelecionado.getProfissional().getSubcategoria().getSubcategoria());
            tvOrcamento.setText("Vou levar cerca de " + pedidoSelecionado.getHorasServico() +"h\nO valor do serviço vai ser " + valorString);
            String fotoPro = pedidoSelecionado.getProfissional().getFoto();
            Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoPro).resize(100,100).rotate(90).into(ivFotoProfissional);


            btnAceitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    vResposta.setVisibility(View.VISIBLE);
                    tvBalaoResposta.setText("Eu aceito! :)");
                    btnAceitar.setVisibility(View.INVISIBLE);
                    btnRecusar.setVisibility(View.INVISIBLE);

                    Call<Pedido> call = new RetroFitConfig().getPedidoService().aceitarOrcamento(tokenCliente, idPedido);
                    call.enqueue(new Callback<Pedido>() {
                        @Override
                        public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                            response.body();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    ivResposta.setImageResource(R.drawable.ic_successg);
                                    tvTextoAlert.setText("Aguarde o profissional em sua casa \nconforme a data e horário\n combinado.");
                                    alertDialog.show();

                                    btnCerto.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                            finish();
                                        }
                                    });


                                }
                            }, 2000);

                        }

                        @Override
                        public void onFailure(Call<Pedido> call, Throwable t) {
                            Log.i("Servico Pendente", t.getMessage());
                        }

                    });


                }
            });

            btnRecusar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    vResposta.setVisibility(View.VISIBLE);
                    tvBalaoResposta.setText("Dessa vez vou recusar,\nmuito obrigada!");
                    btnAceitar.setVisibility(View.INVISIBLE);
                    btnRecusar.setVisibility(View.INVISIBLE);


                    Call<Pedido> call = new RetroFitConfig().getPedidoService().recusarPedidoPendente(tokenCliente,idPedido);
                    call.enqueue(new Callback<Pedido>() {
                        @Override
                        public void onResponse(Call<Pedido> call, Response<Pedido> response) {

                            response.body();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ivResposta.setImageResource(R.drawable.ic_errorg);
                                    tvTextoAlert.setText("Orçamento recusado\n Tente encontrar um novo profissional para o seu problema!");
                                    alertDialog.show();

                                    btnCerto.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                            finish();
                                        }
                                    });


                                }
                            }, 2000);

                        }

                        @Override
                        public void onFailure(Call<Pedido> call, Throwable t) {
                            Log.i("Servico Pendente", t.getMessage());
                        }

                    });

                }
            });

        }

        }

    }


}
