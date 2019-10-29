package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.EnderecoViaCep;
import br.net.daumhelp.recursos.Mascara;
import br.net.daumhelp.model.Endereco;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroEnderecoActivity extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;
    private EditText etCep;
    private EditText etUf;
    private EditText etLogradouro;
    private EditText etBairro;
    private EditText etNumero;
    private EditText etCidade;
    private Button btnCep;
    private Endereco endereco;
    private Long idCidade;
    private EnderecoViaCep enderecoViaCep;


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
        etNumero = findViewById(R.id.et_numero);

        btnProximo.setVisibility(View.INVISIBLE);

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
                    if(validar() == true) {
                        Call<EnderecoViaCep> call = new RetroFitConfig().getEnderecoService().buscarEnderecoViaCep(cep);
                        call.enqueue(new Callback<EnderecoViaCep>() {
                            @Override
                            public void onResponse(Call<EnderecoViaCep> call, Response<EnderecoViaCep> response) {


                                if(response.code() == 404){
                                    etCep.setError("CEP inválido");
                                }else{
                                    carregarEndereco(response.body());
                                    btnProximo.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<EnderecoViaCep> call, Throwable t) {

                                Log.i("Retrofit Endereço" + call, t.getMessage());
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


        /*PEGANDO OS DADOS DA INTENT PASSADA*/
        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais") != null){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais");

            if(listaDados[6].equals("p")){
                etNumero.setVisibility(View.GONE);
            }else{
                etNumero.setVisibility(View.VISIBLE);
            }

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
                        String numero = etNumero.getText().toString();


                        /*ARRAY DO ENDEREÇO PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                        String[] listaEndereco = new String[]{cep, logradouro, bairro, idCidade.toString(), numero};

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

            if(intent.getSerializableExtra("endereco") != null ){
                final String[] listaEndereco = (String[]) intent.getSerializableExtra("endereco");
                etCep.setText(listaEndereco[0]);
            }

        }



    }

    /*MÉTODO DE CARREGAR ENDEREÇO*/
    private void carregarEndereco(EnderecoViaCep enderecoViaCep){
        this.enderecoViaCep = enderecoViaCep;
        etUf.setText(enderecoViaCep.getUf());
        etBairro.setText(enderecoViaCep.getBairro());
        etLogradouro.setText(enderecoViaCep.getLogradouro());
        etCidade.setText(enderecoViaCep.getLocalidade());
        idCidade = enderecoViaCep.getIbge();

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
