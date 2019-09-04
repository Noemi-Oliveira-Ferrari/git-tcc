package br.senai.sp.daumhelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmarEmailActivity extends AppCompatActivity {

        private Button btnConfirmar;
        private TextView tvReenviar;
        private TextView tvAlterar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_confirmacao_pro);

                btnConfirmar = findViewById(R.id.btn_confirmar_email);
                tvAlterar = findViewById(R.id.tv_alterar);

                btnConfirmar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroProfissionalActivity2.class);
                                startActivity(intent);
                        }
                });

                tvAlterar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroProfissionalActivity1.class);
                                startActivity(intent);
                        }
                });


        }


}
