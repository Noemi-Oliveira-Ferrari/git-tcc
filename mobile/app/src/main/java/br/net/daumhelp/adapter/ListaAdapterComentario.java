package br.net.daumhelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.net.daumhelp.R;

import br.net.daumhelp.model.Comentario;

public class ListaAdapterComentario extends ArrayAdapter<Comentario> {

    private Context context;
    private ArrayList<Comentario> lista;


    private TextView tvNome;
    private TextView tvData;
    private TextView tvComentario;
    private Button btnLer;
    private ImageView ivFotoCliente;

    public ListaAdapterComentario(Context context, ArrayList<Comentario> lista){
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        Comentario comentarioPosicao = this.lista.get(position);
//        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_lista_comentario, null);
//
//          ivFotoCliente = convertView.findViewById(R.id.profile_image);
////        Picasso.get().load("").into(ivFotoCliente);
//
//        tvComentario = convertView.findViewById(R.id.tv_comentario);
//        tvComentario.setText(comentarioPosicao.getComentario());
//
//        tvNome = convertView.findViewById(R.id.tv_nome);
//        tvNome.setText(comentarioPosicao.getNome());
//
//        tvData = convertView.findViewById(R.id.tv_data_comentario);
//        tvData.setText(comentarioPosicao.getData());
//
//        return convertView;
//
//    }


}
