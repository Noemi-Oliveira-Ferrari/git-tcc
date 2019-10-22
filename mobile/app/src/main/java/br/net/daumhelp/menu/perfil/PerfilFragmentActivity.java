package br.net.daumhelp.menu.perfil;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.net.daumhelp.R;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import br.net.daumhelp.EditarActivity;
import br.net.daumhelp.adapter.ListaAdapterComentario;
import br.net.daumhelp.model.Comentario;
import br.net.daumhelp.model.Profissional;


public class PerfilFragmentActivity extends Fragment {

    private PerfilViewModel perfilViewModel;
    private ListView lista;
    private ImageButton btnConfig;
    private Profissional profissional;
    private TextView tvNome;
    private TextView tvLocal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.activity_fragment_perfil, container, false);


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

        ListView listView = (ListView)getView().findViewById(R.id.lv_comentarios);
        listView.setAdapter(listaComentario);

        btnConfig = getView().findViewById(R.id.ic_config);
        tvLocal = getView().findViewById(R.id.tv_local);
        tvNome = getView().findViewById(R.id.tv_nome);


        Intent intent = getActivity().getIntent();
        if (intent.getSerializableExtra("profissional") != null) {

            final Profissional profissional = (Profissional) intent.getSerializableExtra("profissional");

            tvNome.setText(profissional.getNome().toUpperCase());
            tvLocal.setText( profissional.getEndereco().getCidade().getCidade()+ ", " + profissional.getEndereco().getCidade().getMicrorregiao().getUf());

            btnConfig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), EditarActivity.class);
                    intent.putExtra("profissional", profissional);
                    startActivity(intent);
                }
            });

        }




    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}