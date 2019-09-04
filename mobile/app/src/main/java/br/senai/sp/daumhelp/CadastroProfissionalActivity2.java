package br.senai.sp.daumhelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class CadastroProfissionalActivity2 extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pro2);

        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroProfissionalActivity2.this, CadastroProfissionalActivity1.class);
                startActivity(intent);
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*cvOpacity.setVisibility(View.VISIBLE);
                cvSuccess.setVisibility(View.VISIBLE);
                btnVoltar.setVisibility(View.INVISIBLE);*/

                Intent intent = new Intent(CadastroProfissionalActivity2.this, CadastroProfissionalActivity3.class);
                startActivity(intent);
            }
        });



    }
}
