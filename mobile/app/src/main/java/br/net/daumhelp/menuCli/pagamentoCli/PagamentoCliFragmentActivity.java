package br.net.daumhelp.menuCli.pagamentoCli;

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

public class PagamentoCliFragmentActivity extends Fragment {

    private PagamentoCliViewModel pagamentoCliViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pagamentoCliViewModel =
                ViewModelProviders.of(this).get(PagamentoCliViewModel.class);
        View root = inflater.inflate(R.layout.activity_fragment_pagamento, container, false);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(),"Aqui vai ser o pagamento!",Toast.LENGTH_SHORT).show();
    }
}