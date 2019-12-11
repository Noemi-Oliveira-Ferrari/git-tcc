package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EscolhaActivity extends AppCompatActivity {

    private CardView cvProfissional;
    private CardView cvCliente;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha);

        cvProfissional = findViewById(R.id.cv_pro);
        cvCliente = findViewById(R.id.cv_cliente);
        btnVoltar = findViewById(R.id.btn_voltar);

        cvProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaActivity.this, CadastroDadosPessoaisActivity.class);
                intent.putExtra("tipo_usuario", "p");
                startActivity(intent);
            }
        });

        cvCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaActivity.this, CadastroDadosPessoaisActivity.class);
                intent.putExtra("tipo_usuario", "c");
                startActivity(intent);
            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolhaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
