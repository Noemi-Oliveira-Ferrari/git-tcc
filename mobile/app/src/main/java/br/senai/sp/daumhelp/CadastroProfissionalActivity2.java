package br.senai.sp.daumhelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.daumhelp.configretrofit.RetroFitConfig;
import br.senai.sp.daumhelp.mascara.Mascara;
import br.senai.sp.daumhelp.model.Endereco;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroProfissionalActivity2 extends AppCompatActivity{

    private Button btnProximo;
    private Button btnVoltar;
    private EditText etCep;
    private EditText etUf;
    private EditText etLogradouro;
    private EditText etBairro;
    private EditText etCidade;
    private Button btnCep;
    private Endereco endereco;
    private Long idCidade;
    boolean validado = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pro2);

        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);
        etCep = findViewById(R.id.et_cep_pro);
        etUf = findViewById(R.id.et_uf_pro);
        etLogradouro = findViewById(R.id.et_logradouro_pro);
        etBairro = findViewById(R.id.et_bairro_pro);
        etCidade = findViewById(R.id.et_cidade_pro);
        btnCep = findViewById(R.id.btn_gerar_cep);

        // btnProximo.setVisibility(View.INVISIBLE);

        etUf.setEnabled(false);
        etLogradouro.setEnabled(false);
        etBairro.setEnabled(false);
        etCidade.setEnabled(false);


        Mascara maskCep = new Mascara("#####-###", etCep);
        etCep.addTextChangedListener(maskCep);

        btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cep = etCep.getText().toString();
                /*VALIDAÇÃO DO TAMANHO DO CEP*/
                if(etCep.length() == 8 || etCep.length() == 9){

                    Call<Endereco> call = new RetroFitConfig().getEnderecoService().buscarEndereco(cep);
                    call.enqueue(new Callback<Endereco>() {
                        @Override
                        public void onResponse(Call<Endereco> call, Response<Endereco> response) {

                            btnProximo.setVisibility(View.VISIBLE);

                            carregarEndereco(response.body());

                        }

                        @Override
                        public void onFailure(Call<Endereco> call, Throwable t) {
                            Log.i("Retrofit Endereço", t.getMessage());
                        }
                    });

                }else{

                }
            }
        });


        /*PEGANDO OS DADOS DA INTENT PASSADA*/
        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais_pro") != null){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais_pro");

            btnProximo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*PEGANDO OS DADOS DO ENDEREÇO DO USUÁRIO*/
                    String cep = etCep.getText().toString();
                    String uf = etUf.getText().toString();
                    String logradouro = etLogradouro.getText().toString();
                    String bairro = etBairro.getText().toString();
                    String cidade = etCidade.getText().toString();



                    /*ARRAY DO ENDEREÇO PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                    String[] listaEndereco = new String[]{cep, logradouro, bairro, idCidade.toString()};

                    if(validar() == true){
                        Intent intent = new Intent(CadastroProfissionalActivity2.this, CadastroProfissionalActivity3.class);
                        intent.putExtra("endereco_pro", listaEndereco);
                        intent.putExtra("dados_pessoais_pro", listaDados);
                        startActivity(intent);
                    }


                }
            });
        }


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroProfissionalActivity2.this, CadastroProfissionalActivity1.class);
                startActivity(intent);
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

        if(endereco == null){
            etCep.setError("Digite um CPF válido");
            validado = false;
        }

    }

    private boolean validar(){


        if(etCep.getText().toString().isEmpty()){
            etCep.setError("O cpf deve conter 8 digitos");
            validado = false;
        }
        return validado;
    }


}
