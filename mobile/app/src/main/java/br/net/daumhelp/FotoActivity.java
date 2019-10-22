package br.net.daumhelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FotoActivity extends AppCompatActivity {

        private ImageButton ibFoto;
        private Button btnSemFoto;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_foto);

            ibFoto = findViewById(R.id.btn_confirmar_foto);
            btnSemFoto = findViewById(R.id.btn_sem_imagem);

            ibFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


        }
}
