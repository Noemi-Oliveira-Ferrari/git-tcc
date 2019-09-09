package br.senai.sp.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.daumhelp.configretrofit.RetroFitConfig;
import br.senai.sp.daumhelp.model.Categoria;
import br.senai.sp.daumhelp.model.Subcategoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroProfissionalActivity3 extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;

    private List<Categoria> listaCategoria;
    private List<Subcategoria> listaSubcategoria;

    private Spinner spinnerCategoria;
    private Spinner spinnerSubcategoria;
    private EditText etValorHora;
    private EditText etQualificacoes;

    private Long idSub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pro3);


        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);
        etQualificacoes = findViewById(R.id.et_qualificacoes);
        etValorHora = findViewById(R.id.et_valor_hora);
        spinnerCategoria = findViewById(R.id.spn_categoria);
        spinnerSubcategoria = findViewById(R.id.spn_subcategoria);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais_pro") != null ){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais_pro");

            if(intent.getSerializableExtra("endereco_pro") != null ){
                final String[] listaEndereco = (String[]) intent.getSerializableExtra("endereco_pro");

                btnProximo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String qualificacoes = etQualificacoes.getText().toString();
                        String valorHora = etValorHora.getText().toString();


                        String[] listaServ = new String[]{qualificacoes, valorHora, idSub.toString()};

                       // Toast.makeText(CadastroProfissionalActivity3.this, listaServ[2] , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CadastroProfissionalActivity3.this, CadastroProfissionalActivity4.class);
                        intent.putExtra("dados_pessoais_pro", listaDados);
                        intent.putExtra("endereco_pro", listaEndereco);
                        intent.putExtra("serv_pro", listaServ);
                        startActivity(intent);
                    }
                });


            }


        }




        /*CHAMADA DAS CATEGORIAS*/
        Call<List<Categoria>> call = new RetroFitConfig().getCategoriaService().buscarCategorias();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {

                carregarCategorias(response.body());

            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroProfissionalActivity3.this, CadastroProfissionalActivity2.class);
                startActivity(intent);
            }
        });
    }


    /*MÉTODO DE CARREGAR AS CATEGORIAS*/
    private void carregarCategorias(final List<Categoria> listaCategoria){
        this.listaCategoria = listaCategoria;


        final ArrayAdapter<String> adapterCategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaCategoria);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*CHAMADA DAS SUBCATEGORIAS*/
                Call<List<Subcategoria>> callsub = new RetroFitConfig().getSubcategoriaService().buscarSubcategorias((int) spinnerCategoria.getSelectedItemId()+1);
                callsub.enqueue(new Callback<List<Subcategoria>>() {
                    @Override
                    public void onResponse(Call<List<Subcategoria>> callsub, Response<List<Subcategoria>> response) {

                        carregarSubcategorias(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<Subcategoria>> callsub, Throwable t) {
                        Log.i("Retrofit2", t.getMessage());
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /*MÉTODO DE CARREGAR AS SUBCATEGORIAS*/
    private void carregarSubcategorias(final List<Subcategoria> listaSubcategoria){
        this.listaSubcategoria = listaSubcategoria;

        ArrayAdapter<String> adapterSubcategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaSubcategoria);
        adapterSubcategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcategoria.setAdapter(adapterSubcategoria);
        spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Subcategoria subcategoriaId = listaSubcategoria.get(position);

                idSub = subcategoriaId.getIdSubcategoria();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
