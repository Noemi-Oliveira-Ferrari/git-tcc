package br.net.daumhelp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import br.net.daumhelp.PerfilProfissionalBuscaActivity;
import br.net.daumhelp.R;
import br.net.daumhelp.menuCli.homeCli.BuscaFragmentActivity;
import br.net.daumhelp.model.Profissional;

public class ListaAdapterBusca extends ArrayAdapter<Profissional> {

    private Context context;
    private ArrayList<Profissional> lista;

    private TextView tvNome;
    private TextView tvServico;
    private TextView tvValor;
    private TextView tvLocal;
    private Button btnVisualizar;

    public ListaAdapterBusca(@NonNull Context context, ArrayList<Profissional> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       final Profissional listaProfissional = this.lista.get(position);
       convertView = LayoutInflater.from(this.context).inflate(R.layout.adapter_busca_pro, null);

       tvNome = convertView.findViewById(R.id.tv_nome_profissional);
       tvServico = convertView.findViewById(R.id.tv_servico_pro);
       tvValor = convertView.findViewById(R.id.tv_valor_profissional);
       tvLocal = convertView.findViewById(R.id.tv_local_profissional);
        btnVisualizar = convertView.findViewById(R.id.btn_visualizar);

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PerfilProfissionalBuscaActivity.class);
                intent.putExtra("profissionalBusca", listaProfissional);
                context.startActivity(intent);
            }
        });

        tvNome.setText(listaProfissional.getNome());
        tvServico.setText(listaProfissional.getSubcategoria().getSubcategoria());
        tvValor.setText(listaProfissional.getValorHora().toString());
        tvLocal.setText(listaProfissional.getEndereco().getCidade().getCidade() + " - " +  listaProfissional.getEndereco().getCidade().getMicrorregiao().getUf().getUf());



        return convertView;

    }

}
