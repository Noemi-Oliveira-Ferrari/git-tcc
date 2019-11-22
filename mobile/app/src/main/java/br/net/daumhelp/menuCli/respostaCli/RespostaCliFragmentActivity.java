package br.net.daumhelp.menuCli.respostaCli;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.net.daumhelp.R;

public class RespostaCliFragmentActivity extends Fragment {

    private RespostaCliViewModel respostaCliViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        respostaCliViewModel = ViewModelProviders.of(this).get(RespostaCliViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_resposta_cliente, container, false);

        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(),"Aqui vai ser a respostacli!",Toast.LENGTH_SHORT).show();
    }

}
