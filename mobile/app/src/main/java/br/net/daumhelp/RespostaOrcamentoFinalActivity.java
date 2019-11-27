package br.net.daumhelp;

import android.content.Intent;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Pedido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespostaOrcamentoFinalActivity extends AppCompatActivity {


    private String tokenProfissional;
    private Pedido pedido;

    private ImageView ivFoto;
    private ImageView btnVoltar;
    private Button btnFinalizar;
    private TextView tvNomeCliente;
    private TextView tvData;
    private TextView tvHorario;
    private TextView tvQtdHoras;
    private TextView tvValorTotal;
    private TextView tvDescricao;
    private TextView tvCep;
    private TextView tvBairro;
    private TextView tvLogradouro;
    private TextView tvNumero;
    private TextView tvCidadeUf;
    private int idPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta_orcamento_final);

        ivFoto = findViewById(R.id.civ_foto_cli);
        btnVoltar = findViewById(R.id.iv_voltar);
        btnFinalizar = findViewById(R.id.btn_finalizar);
        tvNomeCliente = findViewById(R.id.tv_nome_cli);
        tvData = findViewById(R.id.tv_data);
        tvHorario = findViewById(R.id.tv_horario);
        tvQtdHoras = findViewById(R.id.tv_qtd_horas);
        tvValorTotal = findViewById(R.id.tv_valor_total);
        tvDescricao = findViewById(R.id.tv_descricao);
        tvCep = findViewById(R.id.tv_cep);
        tvBairro = findViewById(R.id.tv_bairro);
        tvLogradouro = findViewById(R.id.tv_rua);
        tvNumero = findViewById(R.id.tv_numero);
        tvCidadeUf = findViewById(R.id.tv_cidade_uf);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("tokenProfissional") != null) {
            tokenProfissional = (String) intent.getSerializableExtra("tokenProfissional");

            if (intent.getSerializableExtra("pedido") != null) {
                pedido = (Pedido) intent.getSerializableExtra("pedido");
                idPedido = pedido.getIdPedido();

                btnVoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                btnFinalizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Call<Pedido> call = new RetroFitConfig().getPedidoService().concluirServico(tokenProfissional, idPedido);
                        call.enqueue(new Callback<Pedido>() {
                            @Override
                            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                                response.body();
                                Toast.makeText(RespostaOrcamentoFinalActivity.this, "Serviço Concluido, Parabens", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Pedido> call, Throwable t) {
                            }

                        });


                    }
                });

                tvNomeCliente.setText(pedido.getCliente().getNome());
                tvData.setText(pedido.getDataServico());
                tvHorario.setText("Das " + pedido.getHorarioInicial() + " às " + pedido.getHorarioFinal());
                tvQtdHoras.setText(pedido.getHorasServico());
                tvValorTotal.setText(pedido.getValorServico().toString());
                tvDescricao.setText(pedido.getDescricao());
                tvCep.setText(pedido.getCliente().getEndereco().getCep());
                tvBairro.setText(pedido.getCliente().getEndereco().getBairro());
                tvLogradouro.setText(pedido.getCliente().getEndereco().getLogradouro());
                tvNumero.setText(String.valueOf(pedido.getCliente().getEndereco().getNumero()));
                tvCidadeUf.setText(pedido.getCliente().getEndereco().getCidade().getCidade()+", "+ pedido.getCliente().getEndereco().getCidade().getMicrorregiao().getUf().getUf());

            }

        }


    }

}
