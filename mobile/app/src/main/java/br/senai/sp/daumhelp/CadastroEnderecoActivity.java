package br.senai.sp.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.daumhelp.configretrofit.RetroFitConfig;
import br.senai.sp.daumhelp.recursos.Mascara;
import br.senai.sp.daumhelp.model.Endereco;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroEnderecoActivity extends AppCompatActivity{

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);
        etCep = findViewById(R.id.et_cep);
        etUf = findViewById(R.id.et_uf);
        etLogradouro = findViewById(R.id.et_logradouro);
        etBairro = findViewById(R.id.et_bairro);
        etCidade = findViewById(R.id.et_cidade);
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
        if(intent.getSerializableExtra("dados_pessoais") != null){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais");

            Toast.makeText(this, listaDados[6], Toast.LENGTH_SHORT).show();

            btnProximo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(validar() == true) {

                        /*PEGANDO OS DADOS DO ENDEREÇO DO USUÁRIO*/
                        String cep = etCep.getText().toString();
                        String uf = etUf.getText().toString();
                        String logradouro = etLogradouro.getText().toString();
                        String bairro = etBairro.getText().toString();
                        String cidade = etCidade.getText().toString();

                        /*ARRAY DO ENDEREÇO PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                        String[] listaEndereco = new String[]{cep, logradouro, bairro, idCidade.toString()};

                        if(listaDados[6].equals("p")){
                            Intent intent = new Intent(CadastroEnderecoActivity.this, CadastroServicoActivity.class);
                            intent.putExtra("endereco", listaEndereco);
                            intent.putExtra("dados_pessoais", listaDados);
                            startActivity(intent);
                        }else if(listaDados[6].equals("c")
                        ){
                            Intent intent = new Intent(CadastroEnderecoActivity.this, CadastroTermosActivity.class);
                            intent.putExtra("endereco", listaEndereco);
                            intent.putExtra("dados_pessoais", listaDados);
                            startActivity(intent);
                        }


                    }
                }
            });

            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CadastroEnderecoActivity.this, CadastroDadosPessoaisActivity.class);
                    intent.putExtra("dados_pessoais", listaDados);
                    intent.putExtra("tipo_usuario", listaDados[6]);
                    startActivity(intent);

                }
            });
        }



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
        if(etCep.getText().toString().length()<9){
            etCep.setError("CEP inválido");
            validado = false;
        }

        return validado;
    }


}
