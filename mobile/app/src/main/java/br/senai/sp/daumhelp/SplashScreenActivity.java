package br.senai.sp.daumhelp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

            final ConnectivityManager conmag = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
            if ( conmag != null ) {
                conmag.getActiveNetworkInfo();
                //Verifica internet pela WIFI
                if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2500);
                }
                //Verifica se tem internet móvel
                if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder alert = new AlertDialog.Builder(SplashScreenActivity.this);
                            alert.setTitle("Conexão").setMessage("Você não está conectado ao WIFI, isso pode gerar cobranças adicionais. \n Deseja continuar?").setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).show();
                        }
                    }, 2500);
                }
            }
    }



}