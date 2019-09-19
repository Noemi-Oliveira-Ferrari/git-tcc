package br.senai.sp.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.senai.sp.daumhelp.configretrofit.RetroFitConfig;
import br.senai.sp.daumhelp.recursos.Data;
import br.senai.sp.daumhelp.recursos.EncryptString;
import br.senai.sp.daumhelp.recursos.GerarCodEmail;
import br.senai.sp.daumhelp.recursos.Mascara;
import br.senai.sp.daumhelp.model.Confirmacao;
import br.senai.sp.daumhelp.recursos.MascaraCpfCnpj;
import br.senai.sp.daumhelp.recursos.ValidarCpf;
import br.senai.sp.daumhelp.recursos.ValidarCpfCnpj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CadastroProfissionalActivity1 extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;
    private EditText etNome;
    private EditText etDataNasc;
    private EditText etCpf;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pro1);

        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);
        etNome = findViewById(R.id.et_nome_pro);
        etDataNasc = findViewById(R.id.et_nasc_pro);
        etCpf = findViewById(R.id.et_cpf_pro);
        etEmail = findViewById(R.id.et_email_pro);
        etSenha = findViewById(R.id.et_senha_pro);
        etConfirmacao = findViewById(R.id.et_confirm_pro);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais_pro") != null) {
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais_pro");
            etNome.setText(listaDados[0]);
            etDataNasc.setText(listaDados[1]);
            etCpf.setText(listaDados[2]);
            etEmail.setText(listaDados[3]);
            etSenha.setText(listaDados[4]);
            etConfirmacao.setText(listaDados[4]);
        }

        Mascara maskData = new Mascara("##/##/####", etDataNasc);

        etDataNasc.addTextChangedListener(maskData);
        etCpf.addTextChangedListener(MascaraCpfCnpj.insert(etCpf));


        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*PEGAR DADOS QUE O USUÁRIO DIGITOU*/
                String nomeProfissional = etNome.getText().toString();
                String dataNascProfissional = etDataNasc.getText().toString();
                String cpfProfissional = etCpf.getText().toString();
                String emailProfissional = etEmail.getText().toString();
                String senhaProfissional = etSenha.getText().toString();
                String senhaCriptografada = EncryptString.gerarHash(senhaProfissional);
                String confirmacaoProfissional = etConfirmacao.getText().toString();

                String codigoEmail = String.valueOf(GerarCodEmail.gerarCodigo());

                String[] listaDados = {""};



                if(etConfirmacao.getText().toString().equals(etSenha.getText().toString())){

                    /*SERIALIZAÇÃO DOS DADOS*/

                    if(validar() == true) {

                        Date data = Data.stringToDate(dataNascProfissional);
                        String dataFormatada = Data.dataToString(data);

                        if(etCpf.getText().length() <= 15){
                            if(ValidarCpfCnpj.calcCpf(cpfProfissional)){
                                /*ARRAY DOS DADOS PESSOAIS PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                                listaDados = new String[]{nomeProfissional, dataFormatada, cpfProfissional, emailProfissional, senhaCriptografada, codigoEmail};
                            } else{
                                etCpf.setError("CPF inválido");
                            }
                        }else{
                            if(ValidarCpfCnpj.calcCnpj(cpfProfissional)){
                                /*ARRAY DOS DADOS PESSOAIS PARA SER LEVADO PRA PRÓXIMA ACTIVITY*/
                                listaDados = new String[]{nomeProfissional, dataNascProfissional, cpfProfissional, emailProfissional, senhaProfissional, codigoEmail};
                                Toast.makeText(CadastroProfissionalActivity1.this, cpfProfissional+"", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(CadastroProfissionalActivity1.this, cpfProfissional+"KKK", Toast.LENGTH_SHORT).show();
                                etCpf.setError("CNPJ inválido");
                            }
                        }



                        Confirmacao confirmacao = new Confirmacao();
                        confirmacao.setCodigoConfirm(String.valueOf(codigoEmail));
                        confirmacao.setDestinatario(emailProfissional);
                        confirmacao.setNome(nomeProfissional);

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

                       Intent intent = new Intent(CadastroProfissionalActivity1.this, ConfirmarEmailActivity.class);
                        intent.putExtra("dados_pessoais_pro", listaDados);
                        startActivity(intent);

                    }

                }else{
                    Toast.makeText(CadastroProfissionalActivity1.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();

                }


            }
        });

        /*VOLTAR PARA A PRÓXIMA ACTIVITY*/
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroProfissionalActivity1.this, EscolhaActivity.class);
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
