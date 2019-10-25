package br.net.daumhelp.menuCli.homeCli;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import br.net.daumhelp.PerfilProfissionalBuscaActivity;
import br.net.daumhelp.R;
import br.net.daumhelp.adapter.ListaAdapterBusca;
import br.net.daumhelp.configretrofit.RetroFitConfig;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BuscaFragmentActivity extends Fragment {


    private TextView tvNome;
    private BuscaViewModel buscaViewModel;
    private Cliente cliente;

    ArrayList<Profissional> lista = new ArrayList<Profissional>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscaViewModel = ViewModelProviders.of(this).get(BuscaViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_busca, container, false);

        getActivity().getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));



        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        Intent intent = getActivity().getIntent();
        if(intent.getSerializableExtra("cliente") != null) {

            cliente = (Cliente) intent.getSerializableExtra("cliente");
            Toast.makeText(getContext(), "" + cliente.getEndereco().getCidade().getMicrorregiao().getIdMicro(), Toast.LENGTH_SHORT).show();
        }

        final ListaAdapterBusca listaProfissional = new ListaAdapterBusca(getContext(), lista);

        int idMicroCliente = cliente.getEndereco().getCidade().getMicrorregiao().getIdMicro();

        tvNome = getView().findViewById(R.id.tv_nome_profissional);

        Call<List<Profissional>> call = new RetroFitConfig().getProfissionalService().buscarProfissionalMicro(idMicroCliente);
        call.enqueue(new Callback<List<Profissional>>() {
            @Override
            public void onResponse(Call<List<Profissional>> call, Response<List<Profissional>> response) {

                lista = (ArrayList<Profissional>) response.body();
                for(Profissional p : lista){
                    listaProfissional.add(p);
                }
                ListView listView = (ListView) getView().findViewById(R.id.lv_busca_pro);
                listView.setAdapter(listaProfissional);

            }

            @Override
            public void onFailure(Call<List<Profissional>> call, Throwable t) {
                Log.i("PROFISSIONAIS BUSCA", t.getMessage());
            }

        });









    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}