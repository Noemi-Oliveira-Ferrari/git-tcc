package br.net.daumhelp.menu.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.net.daumhelp.R;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class PedidosFragmentActivity extends Fragment {

    private PedidosViewModel pedidosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);

        View root = inflater.inflate(R.layout.activity_fragment_pedidos, container, false);

        return root;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Toast.makeText(getActivity(),"Aqui vai ser os pedidos!",Toast.LENGTH_SHORT).show();
    }
}