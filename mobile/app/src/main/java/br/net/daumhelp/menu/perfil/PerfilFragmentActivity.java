package br.net.daumhelp.menu.perfil;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import br.net.daumhelp.R;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.net.daumhelp.EditarActivity;
import br.net.daumhelp.adapter.ListaAdapterComentario;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Avaliacao;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilFragmentActivity extends Fragment {

    private PerfilViewModel perfilViewModel;
    private ImageButton btnConfig;
    private Profissional profissional;
    private TextView tvNome;
    private TextView tvLocal;
    private ImageView ivProfileImg;
    private String tokenProfissional;
    private ListView listView;
    ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.activity_fragment_perfil, container, false);


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

//        ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
//
//        Avaliacao comentario0 = new Avaliacao("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
//        Avaliacao comentario1 = new Avaliacao("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
//        Avaliacao comentario2 = new Avaliacao("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
//        Avaliacao comentario3 = new Avaliacao("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
//        Avaliacao comentario4 = new Avaliacao("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
//
//        lista.add(comentario0);
//        lista.add(comentario1);
//        lista.add(comentario2);
//        lista.add(comentario3);
//        lista.add(comentario4);

//        ListaAdapterComentario listaComentario = new ListaAdapterComentario(getContext(), lista);

//        ListView listView = (ListView) getView().findViewById(R.id.lv_comentarios);
//        listView.setAdapter(listaComentario);




        btnConfig = getView().findViewById(R.id.ic_config);
        tvLocal = getView().findViewById(R.id.tv_local);
        tvNome = getView().findViewById(R.id.tv_nome);
        ivProfileImg = getView().findViewById(R.id.profile_image);

        Intent intent = getActivity().getIntent();

        if (intent.getSerializableExtra("tokenProfissional") != null) {
            tokenProfissional = (String) intent.getSerializableExtra("tokenProfissional");

            if (intent.getSerializableExtra("profissional") != null) {
                profissional = (Profissional) intent.getSerializableExtra("profissional");

                String fotoPro = profissional.getFoto();
                Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoPro).resize(100,100).into(ivProfileImg);

                tvNome.setText(profissional.getNome().toUpperCase());

                tvLocal.setText(profissional.getEndereco().getCidade().getCidade() + ", " + profissional.getEndereco().getCidade().getMicrorregiao().getUf());


                btnConfig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), EditarActivity.class);
                        intent.putExtra("profissional", profissional);
                        intent.putExtra("tokenProfissional", tokenProfissional);
                        startActivity(intent);
                    }
                });


                listView =  getView().findViewById(R.id.lv_comentarios);

                final ListaAdapterComentario listaComentario = new ListaAdapterComentario(getContext(), lista);

                Call<List<Avaliacao>> call = new RetroFitConfig().getComentarioService().carregarComentarios(tokenProfissional, profissional.getIdProfissional());
                call.enqueue(new Callback<List<Avaliacao>>() {
                    @Override
                    public void onResponse(Call<List<Avaliacao>> call, Response<List<Avaliacao>> response) {
                        lista = (ArrayList<Avaliacao>) response.body();
                        if(lista != null){
                            for(Avaliacao a : lista){
                                listaComentario.add(a);
                            }
                            listView.setAdapter(listaComentario);
                            // progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Avaliacao>> call, Throwable t) {
                        Log.i("Servico Pendente", t.getMessage());
                        // progressDialog.dismiss();
                    }

                });


            }
        }



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

        Call<Profissional> call = new RetroFitConfig().getProfissionalService().buscarProfissional(tokenProfissional, profissional.getIdProfissional());
        call.enqueue(new Callback<Profissional>() {
            @Override
            public void onResponse(Call<Profissional> call, Response<Profissional> response) {

               final Profissional profissionalAtualizado = response.body();
               tvNome.setText(profissionalAtualizado.getNome().toUpperCase());
               tvLocal.setText(profissionalAtualizado.getEndereco().getCidade().getCidade() + ", " + profissionalAtualizado.getEndereco().getCidade().getMicrorregiao().getUf());

                btnConfig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), EditarActivity.class);
                        intent.putExtra("profissional", profissionalAtualizado);
                        intent.putExtra("tokenProfissional", tokenProfissional);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<Profissional> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });




    }
}