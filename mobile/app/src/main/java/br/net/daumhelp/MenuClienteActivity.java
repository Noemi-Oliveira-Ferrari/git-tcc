package br.net.daumhelp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.net.daumhelp.model.Profissional;

public class MenuClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
        BottomNavigationView navView = findViewById(R.id.nav_view_cliente);
        getWindow().setStatusBarColor(Color.parseColor("#77C9D4"));

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder( R.id.navigation_dashboard_cliente).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_cliente);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("profissional") != null) {
            Profissional profissional = (Profissional) intent.getSerializableExtra("profissional");
            //Toast.makeText(this, "OI " + profissional.getNome(), Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
