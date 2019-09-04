package br.senai.sp.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pro3);


        btnProximo = findViewById(R.id.btn_proximo);
        btnVoltar = findViewById(R.id.btn_voltar);

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

        Call<List<Subcategoria>> callsub = new RetroFitConfig().getSubcategoriaService().buscarSubcategorias();
        callsub.enqueue(new Callback<List<Subcategoria>>() {
            @Override
            public void onResponse(Call<List<Subcategoria>> callsub, Response<List<Subcategoria>> response) {

                carregarSubcategorias(response.body());

            }

            @Override
            public void onFailure(Call<List<Subcategoria>> callsub, Throwable t) {
                Log.i("Retrofit 2", t.getMessage());
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroProfissionalActivity3.this, CadastroProfissionalActivity4.class);
                startActivity(intent);
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


    private void carregarCategorias(final List<Categoria> listaCategoria){
        this.listaCategoria = listaCategoria;

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaCategoria);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria = (Spinner) findViewById(R.id.spn_categoria);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void carregarSubcategorias(final List<Subcategoria> listaSubcategoria){
        this.listaSubcategoria = listaSubcategoria;

        ArrayAdapter<String> adapterSubcategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaSubcategoria);
        adapterSubcategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcategoria = (Spinner) findViewById(R.id.spn_subcategoria);
        spinnerSubcategoria.setAdapter(adapterSubcategoria);
        spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
