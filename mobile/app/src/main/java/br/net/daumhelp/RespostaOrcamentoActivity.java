package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RespostaOrcamentoActivity extends AppCompatActivity {

    private Button btnAceitar;
    private Button btnRecusar;
    private TextView tvOrcamento;
    private TextView tvNomeProfissional;
    private TextView tvSubcategoriaProfissional;
    private TextView tvBalaoResposta;
    private ImageView ivFotoProfissional;
    private View vResposta;

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


        vResposta.setVisibility(View.INVISIBLE);

        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vResposta.setVisibility(View.VISIBLE);
                tvBalaoResposta.setText("Eu aceito! :)");
                btnAceitar.setVisibility(View.INVISIBLE);
                btnRecusar.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(RespostaOrcamentoActivity.this, "Orçamento aceito, o profissional está vindo até você!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RespostaOrcamentoActivity.this, ComentarioActivity.class);
                        startActivity(intent);
                        //finish();
                    }
                }, 2000);

            }
        });

        btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vResposta.setVisibility(View.VISIBLE);
                tvBalaoResposta.setText("Dessa vez vou recusar,\nmuito obrigada!");
                btnAceitar.setVisibility(View.INVISIBLE);
                btnRecusar.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RespostaOrcamentoActivity.this, "Orçamento recusado =(", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RespostaOrcamentoActivity.this, ComentarioActivity.class);
                        startActivity(intent);
                       // finish();
                    }
                }, 2000);
            }
        });

    }

}
