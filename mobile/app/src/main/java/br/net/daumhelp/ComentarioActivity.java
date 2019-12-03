package br.net.daumhelp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Avaliacao;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentarioActivity  extends AppCompatActivity {

    private String tokenCliente;
    private Profissional profissional;
    private Cliente cliente;
    private ImageView ivFoto;
    private TextView tvNome;
    private TextView tvSubcategoria;
    private EditText etComentario;
    private RatingBar rbEstrelas;
    private Button btnAvaliar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        ivFoto = findViewById(R.id.iv_foto_profissional);
        tvNome = findViewById(R.id.tv_nome_profissional);
        tvSubcategoria = findViewById(R.id.tv_subcategoria);
        etComentario = findViewById(R.id.et_comentario);
        rbEstrelas = findViewById(R.id.rb_avaliacao);
        btnAvaliar = findViewById(R.id.btn_enviar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("tokenCliente") != null) {
            tokenCliente = (String) intent.getSerializableExtra("tokenCliente");

            if (intent.getSerializableExtra("cliente") != null && intent.getSerializableExtra("profissional") != null) {
                profissional = (Profissional) intent.getSerializableExtra("profissional");
                cliente = (Cliente) intent.getSerializableExtra("cliente");

                String fotoPro = profissional.getFoto();
                Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoPro).resize(100,100).into(ivFoto);
                tvNome.setText(profissional.getNome().toUpperCase());
                tvSubcategoria.setText(profissional.getSubcategoria().getSubcategoria());


                btnAvaliar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final ProgressDialog progressDialog = new ProgressDialog(ComentarioActivity.this);
                        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.layout_progressbar);

                        Avaliacao avaliacao = new Avaliacao();
                        avaliacao.setProfissional(profissional);
                        avaliacao.setCliente(cliente);
                        avaliacao.setNota(rbEstrelas.getProgress());
                        avaliacao.setComentario(etComentario.getText().toString());

                        Call<Avaliacao> call = new RetroFitConfig().getComentarioService().comentarProfissional(tokenCliente, avaliacao);
                        call.enqueue(new Callback<Avaliacao>() {

                            @Override
                            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                                response.body();
                                progressDialog.dismiss();
                                Toast.makeText(ComentarioActivity.this, "Obrigado pela avaliação!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            @Override
                            public void onFailure(Call<Avaliacao> call, Throwable t) {
                                Log.i("BOM DIA", t.getMessage());
                                progressDialog.dismiss();
                            }


                        });

                    }
                });

            }
        }

    }
}
