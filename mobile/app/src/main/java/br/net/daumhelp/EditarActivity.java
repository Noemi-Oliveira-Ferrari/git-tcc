package br.net.daumhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import br.net.daumhelp.menu.perfil.PerfilFragmentActivity;

public class EditarActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etData;
    private EditText etCpf;
    private EditText etSenha;
    private EditText etSenhaConfirmacao;
    private EditText etEmail;
    private EditText etCep;
    private EditText etCidade;
    private EditText etLogradouro;
    private EditText etUf;
    private EditText etBairro;
    private TextView tvEditarDados;
    private TextView tvEditarLocal;
    private Button btnCep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        etNome = findViewById(R.id.et_nome_pro);
        etData = findViewById(R.id.et_nasc_pro);
        etCpf = findViewById(R.id.et_cpf_pro);
        etSenha = findViewById(R.id.et_senha_pro);
        etSenhaConfirmacao = findViewById(R.id.et_confirm_pro);
        etEmail = findViewById(R.id.et_email_pro);
        etCep = findViewById(R.id.et_cep_pro);
        etCidade = findViewById(R.id.et_cidade_pro);
        etLogradouro = findViewById(R.id.et_logradouro_pro);
        etUf = findViewById(R.id.et_uf_pro);
        etBairro = findViewById(R.id.et_bairro_pro);
        tvEditarDados = findViewById(R.id.tv_editar_dados);
        tvEditarLocal = findViewById(R.id.tv_editar_local);
        btnCep = findViewById(R.id.btn_gerar_cep);

        etNome.setEnabled(false);
        etData.setEnabled(false);
        etCpf.setEnabled(false);
        etSenha.setEnabled(false);
        etSenhaConfirmacao.setEnabled(false);
        etEmail.setEnabled(false);
        etCep.setEnabled(false);
        etCidade.setEnabled(false);
        etLogradouro.setEnabled(false);
        etUf.setEnabled(false);
        etBairro.setEnabled(false);

        btnCep.setEnabled(false);

        tvEditarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNome.setEnabled(true);
                etData.setEnabled(true);
                etCpf.setEnabled(true);
                etSenha.setEnabled(true);
                etSenhaConfirmacao.setEnabled(true);
                etEmail.setEnabled(true);
                etNome.requestFocus(View.FOCUS_LEFT);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etNome, InputMethodManager.SHOW_IMPLICIT);

                if(tvEditarDados.getText().equals("Editar")){

                    tvEditarDados.setText("Salvar");

                }else if(tvEditarDados.getText().equals("Salvar")){

                    tvEditarDados.setText("Editar");
                    etNome.setEnabled(false);
                    etData.setEnabled(false);
                    etCpf.setEnabled(false);
                    etSenha.setEnabled(false);
                    etSenhaConfirmacao.setEnabled(false);
                    etEmail.setEnabled(false);

                }
            }
        });

        tvEditarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etCep.setEnabled(true);
                btnCep.setEnabled(true);
                btnCep.setTextColor(Color.parseColor("#57BC90"));
                etCep.requestFocus(View.FOCUS_LEFT);


                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etCep, InputMethodManager.SHOW_IMPLICIT);

                if(tvEditarLocal.getText().equals("Editar")){

                    tvEditarLocal.setText("Salvar");

                }else if(tvEditarLocal.getText().equals("Salvar")){

                    tvEditarLocal.setText("Editar");
                    etCep.setEnabled(false);

                }
            }
        });

    }

}
