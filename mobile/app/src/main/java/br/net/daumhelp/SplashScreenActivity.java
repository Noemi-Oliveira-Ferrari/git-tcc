package br.net.daumhelp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ConnectivityManager conmag = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if ( conmag != null ) {
                            conmag.getActiveNetworkInfo();


                            //Verifica internet pela WIFI
                            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                                startActivity(intent);
                                timer.cancel();
                                finish();
                            }


                            //Verifica se tem internet móvel
                            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                                startActivity(intent);
                                timer.cancel();
                                finish();
                            }

                        }
                    }
                },0, 5000);


            }
        }, 3000);




        }
    }