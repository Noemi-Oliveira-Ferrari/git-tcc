package br.net.daumhelp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ComentarioActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

    }
}
