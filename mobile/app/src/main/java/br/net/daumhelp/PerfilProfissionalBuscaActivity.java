package br.net.daumhelp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

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
    private TextView tvNomeAlert;
    private TextView tvCategoriaAlert;
    private TextView tvQualificacoesAlert;

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


        alertDialog = new Dialog(this);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("profissionalBusca") != null) {


            ibSolicitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PerfilProfissionalBuscaActivity.this, DetalhesServicoActivity.class);
                    startActivity(intent);
                    Toast.makeText(PerfilProfissionalBuscaActivity.this, "Agradeço sua preferência =)", Toast.LENGTH_SHORT).show();
                }
            });

            profissionalSelecionado = (Profissional) intent.getSerializableExtra("profissionalBusca");
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

                    tvQualificacoesAlert.setMovementMethod(new ScrollingMovementMethod());

                    tvNomeAlert.setText(profissionalSelecionado.getNome());
                    tvQualificacoesAlert.setText(profissionalSelecionado.getResumoQualificacoes());
                    tvCategoriaAlert.setText(profissionalSelecionado.getSubcategoria().getSubcategoria());

                    alertDialog.show();

                    btnSolicitarAlert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(PerfilProfissionalBuscaActivity.this, DetalhesServicoActivity.class);
                            startActivity(intent);
                            Toast.makeText(PerfilProfissionalBuscaActivity.this, "Agradeço sua preferência =)", Toast.LENGTH_SHORT).show();
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

        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(profissional.getValorHora());
        tvValor.setText(valorString);


        tvServicos.setText("4100");
    }

}
