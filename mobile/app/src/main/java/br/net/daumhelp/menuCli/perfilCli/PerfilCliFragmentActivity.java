package br.net.daumhelp.menuCli.perfilCli;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import br.net.daumhelp.R;
import br.net.daumhelp.adapter.ListaAdapterComentario;
import br.net.daumhelp.model.Comentario;
import br.net.daumhelp.model.Profissional;


public class PerfilCliFragmentActivity extends Fragment {

    private PerfilCliViewModel perfilCliViewModel;
    private ListView lista;
    private ImageButton btnSolicitar;
    private Profissional profissional;
    private TextView tvNome;
    private TextView tvLocal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        perfilCliViewModel = ViewModelProviders.of(this).get(PerfilCliViewModel.class);
        View root = inflater.inflate(R.layout.activity_perfil_pro_busca, container, false);
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ArrayList<Comentario> lista = new ArrayList<Comentario>();

        Comentario comentario0 = new Comentario("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
        Comentario comentario1 = new Comentario("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
        Comentario comentario2 = new Comentario("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
        Comentario comentario3 = new Comentario("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);
        Comentario comentario4 = new Comentario("PEDRO DE ALMEIDA SANTOS", "05/10/2019", "Comentário que o cliente vai fazer sobre o profissional ou serviço ...", 5);

        lista.add(comentario0);
        lista.add(comentario1);
        lista.add(comentario2);
        lista.add(comentario3);
        lista.add(comentario4);

        ListaAdapterComentario listaComentario = new ListaAdapterComentario(getContext(), lista);

        ListView listView = (ListView) getView().findViewById(R.id.lv_comentarios);
        listView.setAdapter(listaComentario);

        btnSolicitar = getView().findViewById(R.id.ic_solicitar);
        tvLocal = getView().findViewById(R.id.tv_local);
        tvNome = getView().findViewById(R.id.tv_nome);


        Intent intent = getActivity().getIntent();
        if (intent.getSerializableExtra("profissional") != null) {

            profissional = (Profissional) intent.getSerializableExtra("profissional");

            Toast.makeText(getContext(), "" + profissional.getNome(), Toast.LENGTH_SHORT).show();

            tvNome.setText(profissional.getNome().toUpperCase());
            tvLocal.setText(profissional.getEndereco().getCidade().getCidade() + ", " + profissional.getEndereco().getCidade().getMicrorregiao().getUf());

            btnSolicitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Solicitou", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
    }
}