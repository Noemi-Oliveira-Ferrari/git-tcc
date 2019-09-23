package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class EscolhaActivity extends AppCompatActivity {

    private CardView cvProfissional;
    private CardView cvCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha);

        cvProfissional = findViewById(R.id.cv_pro);
        cvCliente = findViewById(R.id.cv_cliente);

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


    }

}
