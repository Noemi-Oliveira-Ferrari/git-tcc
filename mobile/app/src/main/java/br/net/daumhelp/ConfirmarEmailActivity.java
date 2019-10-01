package br.net.daumhelp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Confirmacao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmarEmailActivity extends AppCompatActivity {

    private Button btnConfirmar;
    private TextView tvReenviar;
    private TextView tvAlterar;
    private EditText etCodigo;
    private TextView tvEmail;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao);

        btnConfirmar = findViewById(R.id.btn_confirmar_email);
        tvAlterar = findViewById(R.id.tv_alterar);
        etCodigo = findViewById(R.id.et_codigo);
        tvEmail = findViewById(R.id.tv_email);
        tvReenviar = findViewById(R.id.tv_reenviar);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais") != null){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais");
            tvEmail.setText(listaDados[3]);

            tvAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConfirmarEmailActivity.this, CadastroDadosPessoaisActivity.class);
                    intent.putExtra("dados_pessoais", listaDados);
                    intent.putExtra("tipo_usuario", listaDados[6]);
                    startActivity(intent);
                }
            });

                tvReenviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /* ENVIANDO O CÓDIGO DE CONFIRMAÇÃO DO EMAIL PARA O USUÁRIO*/
                        Confirmacao confirmacao = new Confirmacao();
                        confirmacao.setCodigoConfirm(String.valueOf(listaDados[5]));
                        confirmacao.setDestinatario(listaDados[3]);
                        confirmacao.setNome(listaDados[0]);

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

                        cont++;
                        if(cont == 3){
                            Toast.makeText(ConfirmarEmailActivity.this, "Tente novamente daqui 10 segundos", Toast.LENGTH_SHORT).show();
                            tvReenviar.setVisibility(View.INVISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvReenviar.setVisibility(View.VISIBLE);
                                    cont = 0;
                                }
                            }, 10000);

                        }else{
                            Toast.makeText(ConfirmarEmailActivity.this, "Código reenviado, verifique o seu e-mail" , Toast.LENGTH_SHORT).show();
                        }


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
