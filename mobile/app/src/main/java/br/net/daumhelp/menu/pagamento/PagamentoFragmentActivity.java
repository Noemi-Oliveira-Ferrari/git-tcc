package br.net.daumhelp.menu.pagamento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.net.daumhelp.R;

public class PagamentoFragmentActivity extends Fragment {

    private PagamentoViewModel pagamentoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pagamentoViewModel =
                ViewModelProviders.of(this).get(PagamentoViewModel.class);
        View root = inflater.inflate(R.layout.activity_fragment_pagamento, container, false);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(),"Aqui vai ser o pagamento!",Toast.LENGTH_SHORT).show();
    }
}