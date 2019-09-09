package br.senai.sp.daumhelp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnEntrar;
    private CardView cvOpacity;
    private CardView cvSucesso;
    private Button btnSucesso;
    private TextView tvSucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnEntrar = findViewById(R.id.btn_entrar);
        cvOpacity = findViewById(R.id.cv_opacity);
        cvSucesso = findViewById(R.id.cv_success);
        btnSucesso = findViewById(R.id.btn_success);
        tvSucesso = findViewById(R.id.tv_txt_sucesso);

        cvSucesso.setVisibility(View.INVISIBLE);
        cvOpacity.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("cadastro") != null){
            int verifCadastroUser = (int)intent.getSerializableExtra("cadastro");
            if(verifCadastroUser == 1){
                tvSucesso.setText("O seu cadastro foi realizado com sucesso.\n Bom Trabalho!");
            }else if(verifCadastroUser == 2){
                tvSucesso.setText("O seu cadastro foi realizado com sucesso.\n Contrate um serviço!");
            }

            cvSucesso.setVisibility(View.VISIBLE);
            cvOpacity.setVisibility(View.VISIBLE);

            btnCadastrar.setEnabled(false);
            btnEntrar.setEnabled(false);
        }

        btnSucesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvSucesso.setVisibility(View.INVISIBLE);
                cvOpacity.setVisibility(View.INVISIBLE);

                btnCadastrar.setEnabled(true);
                btnEntrar.setEnabled(true);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EscolhaActivity.class);
                startActivity(intent);
            }
        });
    }
}
