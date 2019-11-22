package br.net.daumhelp.menu.resposta;

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

public class RespostaFragmentActivity extends Fragment {

    private RespostaViewModel respostaViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        respostaViewModel = ViewModelProviders.of(this).get(RespostaViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_resposta, container, false);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(),"Aqui vai ser a resposta!",Toast.LENGTH_SHORT).show();
    }


}
