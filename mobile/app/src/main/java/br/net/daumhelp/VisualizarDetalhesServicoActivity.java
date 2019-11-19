package br.net.daumhelp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VisualizarDetalhesServicoActivity extends AppCompatActivity {


    private ImageView foto1;
    private ImageView foto2;
    private ImageView foto3;
    private TextView hora;
    private TextView data;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servico);


        foto1 = findViewById(R.id.civ_foto1);
        foto2 = findViewById(R.id.civ_foto2);
        foto3 = findViewById(R.id.civ_foto3);
        hora = findViewById(R.id.tv_horario);
        data = findViewById(R.id.tv_data);
        descricao = findViewById(R.id.tv_descricao);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("pedidoPendente") != null) {
          int id = (int) intent.getSerializableExtra("pedidoPendente");
            Toast.makeText(this, "id->" + id, Toast.LENGTH_SHORT).show();
        }



    }

}
