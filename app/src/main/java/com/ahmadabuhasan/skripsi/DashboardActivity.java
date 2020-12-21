package com.ahmadabuhasan.skripsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

public class DashboardActivity extends AppCompatActivity {

    CardView cardViewKaca;
    CardView cardViewPigura;
    CardView cardViewKasir;
    CardView cardViewData;
    CardView cardViewPrint;
    CardView cardViewSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardViewKaca = findViewById(R.id.card_kaca);
        cardViewPigura = findViewById(R.id.card_pigura);
        cardViewKasir = findViewById(R.id.card_kasir);
        cardViewData = findViewById(R.id.card_data);
        cardViewPrint = findViewById(R.id.card_print);
        cardViewSettings = findViewById(R.id.card_settings);

    }
}