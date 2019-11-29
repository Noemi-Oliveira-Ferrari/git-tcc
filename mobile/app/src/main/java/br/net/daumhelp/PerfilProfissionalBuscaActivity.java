package br.net.daumhelp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import de.hdodenhof.circleimageview.CircleImageView;


public class PerfilProfissionalBuscaActivity extends AppCompatActivity {

    private Profissional profissionalSelecionado;
    private CircleImageView civFotoProfissional;
    private TextView tvNome;
    private TextView tvLocal;
    private TextView tvValor;
    private TextView tvServicos;
    private Button btnVisualizar;
    private ImageButton ibSolicitar;
    private ImageButton ibAvaliar;
    private ImageButton ibFavoritar;

    private Dialog alertDialog;
    private Button btnSolicitarAlert;
    private Button btnSair;
    private ImageButton btnAvaliar;
    private TextView tvNomeAlert;
    private TextView tvCategoriaAlert;
    private TextView tvQualificacoesAlert;
    private Cliente clienteLogado;
    private ImageView ivFotoProfissional;
    private ImageView ivFotoProfissionalAlert;
    private String tokenCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pro_busca);

        getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        civFotoProfissional = findViewById(R.id.profile_image);
        tvNome = findViewById(R.id.tv_nome);
        tvLocal = findViewById(R.id.tv_local);
        tvValor = findViewById(R.id.tv_valor_hora);
        tvServicos = findViewById(R.id.tv_serv_realizado);
        ibSolicitar = findViewById(R.id.ic_solicitar);
        ibAvaliar = findViewById(R.id.ic_avaliar);
        ibFavoritar = findViewById(R.id.ic_salvar);
        btnVisualizar = findViewById(R.id.btn_resumo);
        ivFotoProfissional = findViewById(R.id.profile_image);
        btnAvaliar = findViewById(R.id.ic_avaliar);


        alertDialog = new Dialog(this);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("tokenCliente") != null) {
            tokenCliente = (String) intent.getSerializableExtra("tokenCliente");
        }

        if (intent.getSerializableExtra("profissionalBusca") != null && intent.getSerializableExtra("cliente") != null) {

            profissionalSelecionado = (Profissional) intent.getSerializableExtra("profissionalBusca");
            clienteLogado = (Cliente) intent.getSerializableExtra("cliente");

            btnAvaliar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PerfilProfissionalBuscaActivity.this, ComentarioActivity.class);
                    intent.putExtra("profissional", profissionalSelecionado);
                    intent.putExtra("cliente", clienteLogado);
                    intent.putExtra("tokenCliente", tokenCliente);
                    startActivity(intent);
                }
            });

            ibSolicitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PerfilProfissionalBuscaActivity.this, DetalhesSolicitacaoServicoActivity.class);
                    intent.putExtra("profissionalSolicitado", profissionalSelecionado);
                    intent.putExtra("clienteLogado", clienteLogado);
                    intent.putExtra("tokenCliente", tokenCliente);
                    startActivity(intent);
                }
            });


            carregarDados(profissionalSelecionado);

            btnVisualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.setContentView(R.layout.adapter_alert);

                    btnSolicitarAlert = alertDialog.findViewById(R.id.btn_solicitar_alert);
                    btnSair = alertDialog.findViewById(R.id.ib_sair);
                    tvNomeAlert = alertDialog.findViewById(R.id.tv_nome_pro_alert);
                    tvCategoriaAlert = alertDialog.findViewById(R.id.tv_nome_cat_alert);
                    tvQualificacoesAlert = alertDialog.findViewById(R.id.tv_qualificacoes_alert);
                    ivFotoProfissionalAlert = alertDialog.findViewById(R.id.profile_image_alert);

                    tvQualificacoesAlert.setMovementMethod(new ScrollingMovementMethod());

                    tvNomeAlert.setText(profissionalSelecionado.getNome());
                    tvQualificacoesAlert.setText(profissionalSelecionado.getResumoQualificacoes());
                    tvCategoriaAlert.setText(profissionalSelecionado.getSubcategoria().getSubcategoria());

                    String fotoPro = profissionalSelecionado.getFoto();
                    Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoPro).resize(100,100).rotate(90).into(ivFotoProfissionalAlert);

                    alertDialog.show();

                    btnSolicitarAlert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(PerfilProfissionalBuscaActivity.this, DetalhesSolicitacaoServicoActivity.class);
                            startActivity(intent);


                        }
                    });

                    btnSair.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }
            });

        }
    }


    private void carregarDados(Profissional profissional){
        tvNome.setText(profissional.getNome().toUpperCase());
        tvLocal.setText(profissional.getEndereco().getCidade().getCidade() + ", " + profissional.getEndereco().getCidade().getMicrorregiao().getUf().getUf());

        String fotoPro = profissional.getFoto();
        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoPro).resize(100,100).rotate(90).into(ivFotoProfissional);

        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(profissional.getValorHora());
        tvValor.setText(valorString);


        tvServicos.setText("4100");
    }

}
