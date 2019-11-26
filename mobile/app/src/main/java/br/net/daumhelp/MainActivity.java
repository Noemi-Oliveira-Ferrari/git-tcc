package br.net.daumhelp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


//import org.apache.commons.codec.binary.Base64;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.JwtToken;
import br.net.daumhelp.model.Login;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.model.TokenBodyCliente;
import br.net.daumhelp.model.TokenBodyProfissional;
import br.net.daumhelp.recursos.Base64Utils;
import br.net.daumhelp.recursos.EncryptString;
import br.net.daumhelp.recursos.HandleJson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnEntrar;
    private CardView cvOpacity;
    private CardView cvSucesso;
    private Button btnSucesso;
    private TextView tvSucesso;
    private EditText etSenha;
    private EditText etEmail;
//    private ProgressBar pbLoading;
//    private Base64 base64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnEntrar = findViewById(R.id.btn_entrar);
        cvOpacity = findViewById(R.id.cv_opacity);
        cvSucesso = findViewById(R.id.cv_success);
        btnSucesso = findViewById(R.id.btn_success);
        tvSucesso = findViewById(R.id.tv_txt_sucesso);
        etEmail = findViewById(R.id.et_login_email);
        etSenha = findViewById(R.id.et_login_senha);
//        pbLoading = findViewById(R.id.pb_loading);

        cvSucesso.setVisibility(View.INVISIBLE);
        cvOpacity.setVisibility(View.INVISIBLE);


        //etEmail.setText("noemi@noemi.comc");
        etSenha.setText("123123123");


        /*VERIFICAÇÃO DA CONEXÃO*/
        final ConnectivityManager conmag = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conmag != null ) {
            conmag.getActiveNetworkInfo();

            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Conexão").setMessage("Você não está conectado ao WIFI, isso pode gerar cobranças adicionais. \n Deseja continuar?").setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
            }
        }



        Intent intent = getIntent();
        if(intent.getSerializableExtra("cadastro") != null){
            int verifCadastroUser = (int)intent.getSerializableExtra("cadastro");
            if(verifCadastroUser == 1){
                tvSucesso.setText("O seu cadastro foi realizado com sucesso.\n Bom Trabalho!");
            }else if(verifCadastroUser == 2){
                tvSucesso.setText("O seu cadastro foi realizado com sucesso.\n Contrate um serviço!");
            }

            cvSucesso.setVisibility(View.VISIBLE);
            cvOpacity.setVisibility(View.VISIBLE);

            btnCadastrar.setEnabled(false);
            btnEntrar.setEnabled(false);
        }

        btnSucesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cvSucesso.setVisibility(View.INVISIBLE);
                cvOpacity.setVisibility(View.INVISIBLE);

                btnCadastrar.setEnabled(true);
                btnEntrar.setEnabled(true);
            }
        });


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_progressbar);

                final String senha = EncryptString.gerarHash(etSenha.getText().toString());
                final String email = etEmail.getText().toString();

                final Login login = new Login();
                login.setEmail(email);
                login.setSenha(senha);

                Call<JwtToken> call = new RetroFitConfig().getLoginService().buscarPro(login);
                call.enqueue(new Callback<JwtToken>() {
                    @Override
                    public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {

                        progressDialog.dismiss();
                        String data = null;
                        String tokenBody = null;
                        String token = response.body().getToken();

                        try {
                            tokenBody = Base64Utils.getTokenBody(token);
                            data = Base64Utils.getJson(tokenBody);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        TokenBodyProfissional tokenBodyProfissional = HandleJson.getJsonTokenBodyProfissional(data);
                        Profissional profissionalToken = tokenBodyProfissional.getProfissional();
                        Log.d("TOKEN", profissionalToken.getIdProfissional() + " <----");

                        if(profissionalToken.getFoto() == null){

                            Intent intent = new Intent(MainActivity.this, CadastroFotoActivity.class);
                            intent.putExtra("profissional", profissionalToken);
                            intent.putExtra("tokenProfissional", token);
                            startActivity(intent);
                            finish();

                        }else{

                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            intent.putExtra("profissional", profissionalToken);
                            intent.putExtra("tokenProfissional", token);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<JwtToken> call, Throwable t) {

//                        Login login = new Login();
                        login.setEmail(email);
                        login.setSenha(senha);

                        Call<JwtToken> call2 = new RetroFitConfig().getLoginService().buscarCli(login);
                        call2.enqueue(new Callback<JwtToken>() {
                            @Override
                            public void onResponse(Call<JwtToken> call2, Response<JwtToken> response) {

                                String data = null;
                                String tokenBody = null;
                                String token = response.body().getToken();

                                try {
                                    tokenBody = Base64Utils.getTokenBody(token);
                                    data = Base64Utils.getJson(tokenBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                TokenBodyCliente tokenBodyCliente = HandleJson.getJsonTokenBodyCliente(data);
                                Cliente clienteToken = tokenBodyCliente.getCliente();
                                Log.d("TOKEN", clienteToken.getIdCliente() + " <----");

                                if(tokenBodyCliente.getCliente().getFoto() == null){

                                    Intent intent = new Intent(MainActivity.this, CadastroFotoActivity.class);
                                    intent.putExtra("cliente", clienteToken);
                                    intent.putExtra("tokenCliente", token);
                                    startActivity(intent);

                                }else{

                                    Intent intent = new Intent(MainActivity.this, MenuClienteActivity.class);
                                    intent.putExtra("cliente", clienteToken);
                                    intent.putExtra("tokenCliente", token);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onFailure(Call<JwtToken> call, Throwable t) {
                                Log.i("Retrofit LOGIN", t.getMessage());
                                Toast.makeText(MainActivity.this, "Esse usuario não existe", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                Intent intent = new Intent(MainActivity.this, EscolhaActivity.class);
            }
        });


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, EscolhaActivity.class);
                startActivity(intent);

            }
        });
    }
}