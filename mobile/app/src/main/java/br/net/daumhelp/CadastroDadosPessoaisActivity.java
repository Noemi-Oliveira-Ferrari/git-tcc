package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.net.daumhelp.recursos.ValidarCpfCnpj;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.recursos.GerarCodEmail;
import br.net.daumhelp.recursos.Mascara;
import br.net.daumhelp.model.Confirmacao;
import br.net.daumhelp.recursos.MascaraCpfCnpj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CadastroDadosPessoaisActivity extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;
    private EditText etNome;
    private EditText etDataNasc;
    private EditText etCpf;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmacao;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_dados_pessoais);

        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);
        etNome = findViewById(R.id.et_nome);
        etDataNasc = findViewById(R.id.et_nasc);
        etCpf = findViewById(R.id.et_cpf);
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senha);
        etConfirmacao = findViewById(R.id.et_confirm);
        tvTitulo = findViewById(R.id.tv_titulo);

        Mascara maskData = new Mascara("##/##/####", etDataNasc);
        etDataNasc.addTextChangedListener(maskData);

        etNome.setText("pedro");
        etDataNasc.setText("03/10/2000");
        etCpf.setText("503.356.118-93");
        etEmail.setText("noemi@noemi");
        etSenha.setText("123123123");
        etConfirmacao.setText("123123123");

        final Intent intent = getIntent();

        if(intent.getSerializableExtra("tipo_usuario") != null) {

            String tipoUsuario = (String) intent.getSerializableExtra("tipo_usuario");


            if(tipoUsuario.equals("c")){
                Mascara maskCpf = new Mascara("###.###.###-##", etCpf);
                etCpf.addTextChangedListener(maskCpf);
                tvTitulo.setText("Cadastre-se para contratar \n serviços");
                etCpf.setHint("CPF");

            }else if(tipoUsuario.equals("p")){
                tvTitulo.setText("Cadastre-se para oferecer \n seus serviços");
                etCpf.setHint("CPF/CNPJ");
                etCpf.addTextChangedListener(MascaraCpfCnpj.insert(etCpf));
            }
        }

        if(intent.getSerializableExtra("dados_pessoais") != null) {
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais");
            etNome.setText(listaDados[0]);
            etDataNasc.setText(listaDados[1]);
            etCpf.setText(listaDados[2]);
            etEmail.setText(listaDados[3]);
            etSenha.setText(listaDados[4]);
            etConfirmacao.setText(listaDados[4]);

            if(listaDados[6].equals("c")){
                Mascara maskCpf = new Mascara("###.###.###-##", etCpf);
                etCpf.addTextChangedListener(maskCpf);
                tvTitulo.setText("Cadastre-se para contratar \n serviços");
                etCpf.setHint("CPF");

            }else if(listaDados[6].equals('p')){
                tvTitulo.setText("Cadastre-se para oferecer \n seus serviços");
                etCpf.setHint("CPF/CNPJ");
                etCpf.addTextChangedListener(MascaraCpfCnpj.insert(etCpf));
            }

        }

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*PEGAR DADOS QUE O USUÁRIO DIGITOU*/
                String nome = etNome.getText().toString();
                String dataNasc = etDataNasc.getText().toString();
                String cpf = etCpf.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String confirmacaoSenha = etConfirmacao.getText().toString();

                String codigoEmail = String.valueOf(GerarCodEmail.gerarCodigo());

                String[] listaDados = {""};

                if(etConfirmacao.getText().toString().equals(etSenha.getText().toString())){

                    /*SERIALIZAÇÃO DOS DADOS*/
                    if(validar() == true) {

                        if(etCpf.getText().length() <= 15){
                            if(ValidarCpfCnpj.calcCpf(cpf)){
                                /*ARRAY DOS DADOS PESSOAIS PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                                String tipoUsuario = (String) intent.getSerializableExtra("tipo_usuario");
                                listaDados = new String[]{nome, dataNasc, cpf, email, senha, codigoEmail, tipoUsuario};

                                /* ENVIANDO O CÓDIGO DE CONFIRMAÇÃO DO EMAIL PARA O USUÁRIO*/
                                Confirmacao confirmacao = new Confirmacao();
                                confirmacao.setCodigoConfirm(String.valueOf(codigoEmail));
                                confirmacao.setDestinatario(email);
                                confirmacao.setNome(nome);

                                /*LEMBRAR DE FAZER CONFIRMAR EMAIL PARA OS DOIS TIPOS DE USUARIO*/
                                Call<Confirmacao> call = new RetroFitConfig().getProfissionalService().confirmarEmail(confirmacao);
                                call.enqueue(new Callback<Confirmacao>() {
                                    @Override
                                    public void onResponse(Call<Confirmacao> call, Response<Confirmacao> response) {
                                        response.body();
                                    }

                                    @Override
                                    public void onFailure(Call<Confirmacao> call, Throwable t) {
                                        Log.i("Retrofit Email", t.getMessage());
                                    }
                                });

                                Intent intent = new Intent(CadastroDadosPessoaisActivity.this, ConfirmarEmailActivity.class);
                                intent.putExtra("dados_pessoais", listaDados);
                                startActivity(intent);
                            } else{
                                etCpf.setError("CPF inválido");
                            }
                        }else {
                            if (ValidarCpfCnpj.calcCnpj(cpf)) {
                                /*ARRAY DOS DADOS PESSOAIS PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                                String tipoUsuario = (String) intent.getSerializableExtra("tipo_usuario");
                                listaDados = new String[]{nome, dataNasc, cpf, email, senha, codigoEmail, tipoUsuario};

                                /* ENVIANDO O CÓDIGO DE CONFIRMAÇÃO DO EMAIL PARA O USUÁRIO*/
                                Confirmacao confirmacao = new Confirmacao();
                                confirmacao.setCodigoConfirm(String.valueOf(codigoEmail));
                                confirmacao.setDestinatario(email);
                                confirmacao.setNome(nome);

                                /*LEMBRAR DE FAZER CONFIRMAR EMAIL PARA OS DOIS TIPOS DE USUARIO*/
                                Call<Confirmacao> call = new RetroFitConfig().getProfissionalService().confirmarEmail(confirmacao);
                                call.enqueue(new Callback<Confirmacao>() {
                                    @Override
                                    public void onResponse(Call<Confirmacao> call, Response<Confirmacao> response) {
                                        response.body();
                                    }

                                    @Override
                                    public void onFailure(Call<Confirmacao> call, Throwable t) {
                                        Log.i("Retrofit Email", t.getMessage());
                                    }
                                });

                                Intent intent = new Intent(CadastroDadosPessoaisActivity.this, ConfirmarEmailActivity.class);
                                intent.putExtra("dados_pessoais", listaDados);
                                startActivity(intent);
                            } else {
                                etCpf.setError("CNPJ inválido");
                            }
                        }

                    }
                }else{

                    Toast.makeText(CadastroDadosPessoaisActivity.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();

                }
            }
        });

        /*VOLTAR PARA A PRÓXIMA ACTIVITY*/
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroDadosPessoaisActivity.this, EscolhaActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validar(){
        boolean validado = true;

        if(etNome.getText().toString().isEmpty()){
            etNome.setError("Digite seu nome por favor");
            validado = false;
        }
        if(etNome.getText().length()<=2){
            etNome.setError("O nome deve conter no min. 3 caracteres");
            validado = false;
        }
        if(etEmail.getText().toString().isEmpty()){
            validado = false;
        }
        if(etEmail.getText().length()<9){
            etEmail.setError("O e-mail deve conter no min. 9 caracteres");
            validado = false;
        }
        if(etCpf.getText().toString().isEmpty()){
            validado = false;
        }
        if(etCpf.getText().length()<14){
            etCpf.setError("CPF/CNPJ inválido");
            validado = false;
        }
        if(etSenha.getText().toString().isEmpty()){
            validado = false;
        }
        if(etSenha.getText().length()<8){
            etSenha.setError("A senha deve conter no min. 8 caracteres");
            validado = false;
        }
        if(etDataNasc.getText().toString().isEmpty()){
            validado = false;
        }
        if(etDataNasc.getText().length()<10){
            etDataNasc.setError("Data de nascimento inválida");
            validado = false;
        }
        return validado;
    }



}
