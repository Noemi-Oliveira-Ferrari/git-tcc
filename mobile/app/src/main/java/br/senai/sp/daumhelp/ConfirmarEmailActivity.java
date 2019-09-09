package br.senai.sp.daumhelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.senai.sp.daumhelp.mascara.Mascara;

public class ConfirmarEmailActivity extends AppCompatActivity {

    private Button btnConfirmar;
    private TextView tvReenviar;
    private TextView tvAlterar;
    private EditText etCodigo;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_pro);

        btnConfirmar = findViewById(R.id.btn_confirmar_email);
        tvAlterar = findViewById(R.id.tv_alterar);
        etCodigo = findViewById(R.id.et_codigo);
        tvEmail = findViewById(R.id.tv_email);


        Mascara maskConfirm = new Mascara("#  #  #  #", etCodigo);
        etCodigo.addTextChangedListener(maskConfirm);

        tvAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroProfissionalActivity1.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais_pro") != null){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais_pro");
            tvEmail.setText(listaDados[3]);

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(etCodigo.getText().toString().equals("")){

                        Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroProfissionalActivity2.class);
                        intent.putExtra("dados_pessoais_pro", listaDados);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ConfirmarEmailActivity.this, "Código incorreto", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }




}
