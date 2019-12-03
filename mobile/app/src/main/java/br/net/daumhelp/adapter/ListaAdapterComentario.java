package br.net.daumhelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import br.net.daumhelp.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import br.net.daumhelp.model.Avaliacao;
import br.net.daumhelp.recursos.Data;

public class ListaAdapterComentario extends ArrayAdapter<Avaliacao> {

    private Context context;
    private ArrayList<Avaliacao> lista;

    private TextView tvNome;
    private TextView tvData;
    private TextView tvComentario;
    private Button btnLer;
    private ImageView ivFotoCliente;
    private RatingBar rbNota;

    public ListaAdapterComentario(Context context, ArrayList<Avaliacao> lista){
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Avaliacao comentarioPosicao = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_lista_comentario, null);

        ivFotoCliente = convertView.findViewById(R.id.profile_image);
        String fotoCli = comentarioPosicao.getCliente().getFoto();
        Picasso.get().load("http://ec2-3-220-68-195.compute-1.amazonaws.com/" + fotoCli).resize(100,100).into(ivFotoCliente);

        tvComentario = convertView.findViewById(R.id.tv_comentario);
        tvComentario.setText(comentarioPosicao.getComentario());

        tvNome = convertView.findViewById(R.id.tv_nome);
        tvNome.setText(comentarioPosicao.getCliente().getNome());

        rbNota = convertView.findViewById(R.id.    rb_comentario);
        rbNota.setProgress(comentarioPosicao.getNota());

        tvData = convertView.findViewById(R.id.tv_data_comentario);

        Date dataPedido = Data.usStringToDate(comentarioPosicao.getDataAvaliacao());
        String dataFormatada = Data.dataToBrString(dataPedido);

        tvData.setText(dataFormatada);

        return convertView;

    }


}
