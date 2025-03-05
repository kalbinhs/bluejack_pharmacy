package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    HomeSection hs;
    TransactionSection ts;
    Button btnHome, btnTransaction, btnLogout, btnAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnLogout = findViewById(R.id.btnLogout);
        btnHome = findViewById(R.id.btnHome);
        btnTransaction = findViewById(R.id.btnTransaction);
        btnAboutUs = findViewById(R.id.btnAboutUs);

        hs = new HomeSection();
        ts = new TransactionSection();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flcontainer,hs).commit();

        btnHome.setOnClickListener(e->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flcontainer,hs).commit();
        });

        btnTransaction.setOnClickListener(e->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flcontainer,ts).commit();
        });

        btnLogout.setOnClickListener(e->{
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });

        btnAboutUs.setOnClickListener(e->{
            Intent intent = new Intent(this, AboutUs.class);
            startActivity(intent);
        });
    }
}