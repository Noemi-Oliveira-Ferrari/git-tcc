package br.net.daumhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.menu.perfil.PerfilFragmentActivity;
import br.net.daumhelp.model.Endereco;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.recursos.Mascara;
import br.net.daumhelp.recursos.MascaraCpfCnpj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private TextView tvNome;
    private Button btnCep;
    private Profissional profissional;
    private Endereco endereco;
    private Long idCidade;
    private ImageView ivToolbar;

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
        tvNome = findViewById(R.id.tv_nome);
        ivToolbar = findViewById(R.id.iv_toolbar);

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

        Mascara maskCep = new Mascara("#####-###", etCep);
        etCep.addTextChangedListener(maskCep);

       /*Mascara maskData = new Mascara("##/##/####", etData);
        etData.addTextChangedListener(maskData);*/

        etCpf.addTextChangedListener(MascaraCpfCnpj.insert(etCpf));

        ivToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        if (intent.getSerializableExtra("profissional") != null) {

            profissional = (Profissional) intent.getSerializableExtra("profissional");

            etNome.setText(profissional.getNome());
            tvNome.setText(profissional.getNome());
            etData.setText(profissional.getDataNasc());
            etCpf.setText(profissional.getCpf());
            etEmail.setText(profissional.getEmail());
            etCep.setText(profissional.getEndereco().getCep());
            etCidade.setText(profissional.getEndereco().getCidade().getCidade());
            etLogradouro.setText(profissional.getEndereco().getLogradouro());
            etUf.setText(profissional.getEndereco().getCidade().getMicrorregiao().getUf().getUf());
            etBairro.setText(profissional.getEndereco().getBairro());

        }

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

                    btnCep.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String cep = etCep.getText().toString();
                            /*VALIDAÇÃO DO TAMANHO DO CEP*/
                            if(etCep.length() == 8 || etCep.length() == 9){
                                if(validar() == true) {
                                    Call<Endereco> call = new RetroFitConfig().getEnderecoService().buscarEndereco(cep);
                                    call.enqueue(new Callback<Endereco>() {
                                        @Override
                                        public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                                            if(response.body().getCidade() == null){

                                                etCep.setError("CEP inválido");
                                            }else{

                                                carregarEndereco(response.body());
                                            }


                                        }

                                        @Override
                                        public void onFailure(Call<Endereco> call, Throwable t) {
                                            Log.i("Retrofit Endereço", t.getMessage());
                                            etCep.setError("CEP inválido");
                                        }

                                    });
                                }else{
                                    etCep.setError("CEP inválido");
                                }
                            }else{
                                etCep.setError("CEP inválido");
                            }
                        }
                    });



                }else if(tvEditarLocal.getText().equals("Salvar")){

                    btnCep.setTextColor(Color.parseColor("#b1b1b1"));
                    tvEditarLocal.setText("Editar");
                    etCep.setEnabled(false);

                }
            }
        });


    }

    /*MÉTODO DE CARREGAR ENDEREÇO*/
    private void carregarEndereco(Endereco endereco){
        this.endereco = endereco;
        etUf.setText(endereco.getCidade().getMicrorregiao().getUf().toString());
        etBairro.setText(endereco.getBairro());
        etLogradouro.setText(endereco.getLogradouro());
        etCidade.setText(endereco.getCidade().getCidade().toString());
        idCidade = endereco.getCidade().getIdCidade();

    }

    private boolean validar(){
        boolean validado = true;

        if(etCep.getText().toString().isEmpty()){
            etCep.setError("Insira o seu CEP");
            validado = false;
        }
        if(etCep.getText().toString().length() < 9){
            etCep.setError("CEP inválido");
            validado = false;
        }

        return validado;
    }

}
