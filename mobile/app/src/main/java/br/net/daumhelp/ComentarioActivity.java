package br.net.daumhelp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Comentario;
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


                btnAvaliar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Comentario comentario = new Comentario();
                        comentario.setProfissional(profissional);
                        comentario.setCliente(cliente);
                        comentario.setAvaliacao(rbEstrelas.getNumStars());
                        comentario.setComentario(etComentario.getText().toString());

                        Call<Comentario> call = new RetroFitConfig().getComentarioService().comentarProfissional(tokenCliente, comentario);
                        call.enqueue(new Callback<Comentario>() {

                            @Override
                            public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                                response.body();
                            }
                            @Override
                            public void onFailure(Call<Comentario> call, Throwable t) {
                                Log.i("BOM DIA", t.getMessage());
                            }


                        });

                    }
                });

            }
        }

    }
}
