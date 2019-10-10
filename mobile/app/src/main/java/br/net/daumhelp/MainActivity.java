package br.net.daumhelp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Login;
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

        cvSucesso.setVisibility(View.INVISIBLE);
        cvOpacity.setVisibility(View.INVISIBLE);

        etEmail.setText("pedropedro");
        etSenha.setText("123456789");

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

                final String senha = etSenha.getText().toString();
                final String email = etEmail.getText().toString();
                Login login = new Login();
                login.setEmail(email);
                login.setSenha(senha);


                Call<Login> call = new RetroFitConfig().getLoginService().buscarPro(email, senha);
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        response.body();
                        Toast.makeText(MainActivity.this, "PROFISSIONAL LOGADO", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

                        Call<Login> call2 = new RetroFitConfig().getLoginService().buscarCli(email, senha);
                        call2.enqueue(new Callback<Login>() {
                            @Override
                            public void onResponse(Call<Login> call2, Response<Login> response) {
                                response.body();
                                Toast.makeText(MainActivity.this, "CLIENTE LOGADO", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Login> call, Throwable t) {
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
