package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Categoria;
import br.net.daumhelp.model.Subcategoria;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroServicoActivity extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;

    private List<Categoria> listaCategoria;
    private List<Subcategoria> listaSubcategoria;

    private Spinner spinnerCategoria;
    private Spinner spinnerSubcategoria;
    private EditText etValorHora;
    private EditText etQualificacoes;

    private Long idSub;
    private Long idCategoria;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);


        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);
        etQualificacoes = findViewById(R.id.et_qualificacoes);
        etValorHora = findViewById(R.id.et_valor_hora);
        spinnerCategoria = findViewById(R.id.spn_categoria);
        spinnerSubcategoria = findViewById(R.id.spn_subcategoria);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("dados_pessoais") != null ){
            final String[] listaDados = (String[]) intent.getSerializableExtra("dados_pessoais");

            if(intent.getSerializableExtra("endereco") != null ){
                final String[] listaEndereco = (String[]) intent.getSerializableExtra("endereco");

                btnProximo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String qualificacoes = etQualificacoes.getText().toString();
                        String valorHora = etValorHora.getText().toString();


                        String[] listaServ = new String[]{qualificacoes, valorHora, idSub.toString()};

                       // Toast.makeText(CadastroServicoActivity.this, listaServ[2] , Toast.LENGTH_SHORT).show();

                        if(validar() == true){

                            Intent intent = new Intent(CadastroServicoActivity.this, CadastroTermosActivity.class);
                            intent.putExtra("dados_pessoais", listaDados);
                            intent.putExtra("endereco", listaEndereco);
                            intent.putExtra("serv_pro", listaServ);
                            startActivity(intent);

                        }
                    }
                });

                btnVoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CadastroServicoActivity.this, CadastroEnderecoActivity.class);
                        intent.putExtra("dados_pessoais", listaDados);
                        intent.putExtra("endereco", listaEndereco);
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

                Categoria categoriaId = listaCategoria.get(position);

                idCategoria = categoriaId.getIdCategoria();


                /*CHAMADA DAS SUBCATEGORIAS*/
                Call<List<Subcategoria>> callsub = new RetroFitConfig().getSubcategoriaService().buscarSubcategorias(Integer.parseInt(idCategoria.toString()));
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


    private boolean validar(){
        boolean validado = true;

        if(etValorHora.getText().toString().isEmpty()){
            etValorHora.setError("Digite um valor");
            validado = false;
        }

        return validado;
    }


}
