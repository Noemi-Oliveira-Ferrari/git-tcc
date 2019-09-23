package br.senai.sp.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import br.senai.sp.daumhelp.recursos.Data;

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

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais") != null){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais");
            tvEmail.setText(listaDados[3]);

            Toast.makeText(this, listaDados[6] + listaDados[1], Toast.LENGTH_SHORT).show();

            tvAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroDadosPessoaisActivity.class);
                    intent.putExtra("dados_pessoais", listaDados);
                    intent.putExtra("tipo_usuario", listaDados[6]);
                    startActivity(intent);
                }
            });



            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(etCodigo.getText().toString().equals(listaDados[5])){

                        Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroEnderecoActivity.class);
                        intent.putExtra("dados_pessoais", listaDados);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ConfirmarEmailActivity.this, "Código incorreto", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }




}
