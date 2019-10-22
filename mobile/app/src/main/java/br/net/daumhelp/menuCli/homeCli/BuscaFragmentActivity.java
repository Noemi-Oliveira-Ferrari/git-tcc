package br.net.daumhelp.menuCli.homeCli;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.net.daumhelp.R;


public class BuscaFragmentActivity extends Fragment {

    private BuscaViewModel buscaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscaViewModel = ViewModelProviders.of(this).get(BuscaViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_busca, container, false);

        getActivity().getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        return root;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Toast.makeText(getActivity(),"Aqui vai ser os pedidos!",Toast.LENGTH_SHORT).show();
    }
}