package br.net.daumhelp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Pedido;
import br.net.daumhelp.model.Status;
import br.net.daumhelp.recursos.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarDetalhesServicoActivity extends AppCompatActivity {


    private ImageView ivFoto1;
    private ImageView ivFoto2;
    private ImageView ivFoto3;
    private TextView hora;
    private TextView data;
    private TextView descricao;
    private Button btnFazerOrcamento;

    private Dialog dialogOrcamento;
    private Button btnFecharAlert;
    private Button btnEnviarAlert;
    private Button btnAumentarAlert;
    private Button btnDiminuirAlert;
    private TextView tvHorasAlert;
    private TextView tvValorAlert;
    private int horas = 1;
    private String valorHora = "50";
    private Double valorOrcado;
    private String moeda;
    private String token;
    private int idPedido;
    private Pedido pedido;
    private String valorMostrar;
    private String valorString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servico);

        final ProgressDialog progressDialog = new ProgressDialog(VisualizarDetalhesServicoActivity.this);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progressbar);

        ivFoto1 = findViewById(R.id.civ_foto1);
        ivFoto2 = findViewById(R.id.civ_foto2);
        ivFoto3 = findViewById(R.id.civ_foto3);
        hora = findViewById(R.id.tv_horario);
        data = findViewById(R.id.tv_data);
        descricao = findViewById(R.id.tv_descricao);
        btnFazerOrcamento = findViewById(R.id.btn_orcamento);
        dialogOrcamento = new Dialog(VisualizarDetalhesServicoActivity.this);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("tokenProfissional") != null) {
            token = (String) intent.getSerializableExtra("tokenProfissional");
            if (intent.getSerializableExtra("idPedido") != null) {
                idPedido = (int) intent.getSerializableExtra("idPedido");


                Call<Pedido> call = new RetroFitConfig().getPedidoService().buscarPedidosPorId(token, idPedido);
                call.enqueue(new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido>call, Response<Pedido> response) {
                        pedido =  response.body();

                        Date dataPedido = Data.usStringToDate(pedido.getDataServico());
                        String dataFormatada = Data.dataToBrString(dataPedido);
                        data.setText(dataFormatada);
                        hora.setText("Das " + pedido.getHorarioInicial() + " às " +  pedido.getHorarioFinal());
                        descricao.setText(pedido.getDescricao());
                        valorOrcado = pedido.getProfissional().getValorHora();


                        String foto1 = pedido.getFoto1();
                        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + foto1).resize(100,100).rotate(90).into(ivFoto1);

                        String foto2 = pedido.getFoto2();
                        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + foto2).resize(100,100).rotate(90).into(ivFoto2);

                        String foto3 = pedido.getFoto3();
                        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + foto3).resize(100,100).rotate(90).into(ivFoto3);


                        progressDialog.dismiss();

                    }
                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        Log.i("BUSCA-PEDIDO", t.getMessage());
                    }
                });

            }

        }

        btnFazerOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogOrcamento.setContentView(R.layout.adapter_orcamento);

                btnFecharAlert = dialogOrcamento.findViewById(R.id.btn_fechar);
                btnDiminuirAlert = dialogOrcamento.findViewById(R.id.btn_diminuir_horas);
                btnAumentarAlert = dialogOrcamento.findViewById(R.id.btn_aumentar_horas);
                btnEnviarAlert = dialogOrcamento.findViewById(R.id.btn_enviar_orcamento);
                tvHorasAlert = dialogOrcamento.findViewById(R.id.tv_quantidade_horas);
                tvValorAlert = dialogOrcamento.findViewById(R.id.tv_valor_multiplicado);

                tvHorasAlert.setText("1");

                final Double valorHoraProfissional = pedido.getProfissional().getValorHora();
                Locale ptBr = new Locale("pt", "BR");
                valorString = NumberFormat.getCurrencyInstance(ptBr).format(valorHoraProfissional);
                tvValorAlert.setText(valorString);


                btnFecharAlert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //tvValorAlert.setText(valorString);
                        tvHorasAlert.setText("1");
                        valorString = "";
                        horas = 1;
                        dialogOrcamento.dismiss();

                    }
                });

                btnEnviarAlert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(horas == 1){
                            moeda = "hr";
                        }else{
                            moeda = "hrs";
                        }




                        AlertDialog.Builder alert = new AlertDialog.Builder(VisualizarDetalhesServicoActivity.this);
                        alert.setTitle("Confirmar Orçamento").setMessage("Tempo de trabalho: " + horas+moeda + "\nValor: "  + "R$"+valorOrcado).setPositiveButton(" Confirmar Valor", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int id = pedido.getIdPedido();
                                //Pedido pedido = pedido;
                                pedido.setHorasServico(tvHorasAlert.getText().toString());
                                Status status = new Status();
                                status.setIdStatusPedido(2);
                                pedido.setStatus(status);

                                Call<Pedido> call = new RetroFitConfig().getPedidoService().fazerOrcamento(token, id ,pedido);
                                call.enqueue(new Callback<Pedido>() {

                                    @Override
                                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                                        response.body();
                                        finish();

                                    }
                                    @Override
                                    public void onFailure(Call<Pedido> call, Throwable t) {
                                        Log.i("ORÇAMENTO", t.getMessage());
                                    }


                                });

                                Toast.makeText(VisualizarDetalhesServicoActivity.this, "Enviamos, vamos ver se ele aceita ;D", Toast.LENGTH_SHORT).show();
                                dialogOrcamento.dismiss();
                            }
                        }).setNegativeButton("Alterar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                    }
                });

                btnAumentarAlert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        horas = (horas) + 1;
                        tvHorasAlert.setText(String.valueOf(horas));
                        valorOrcado = valorHoraProfissional * horas;
                        valorMostrar = tranformarDinheiroBr(valorOrcado);
                        tvValorAlert.setText(valorMostrar);

                    }
                });

                btnDiminuirAlert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        horas = horas - 1;
                        tvHorasAlert.setText(String.valueOf(horas));
                        valorOrcado = valorHoraProfissional * horas;
                        valorMostrar = tranformarDinheiroBr(valorOrcado);
                        tvValorAlert.setText(valorMostrar);

                    }
                });
                dialogOrcamento.show();

            }
        });

    }

    private String tranformarDinheiroBr(Double valorOrcado){

        Locale ptBr = new Locale("pt", "BR");
        final String valorMostrar = NumberFormat.getCurrencyInstance(ptBr).format(valorOrcado);
        return valorMostrar;

    }

}
