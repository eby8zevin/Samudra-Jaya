package com.ahmadabuhasan.skripsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ahmadabuhasan.skripsi.data.ProductActivity;

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
        this.cardViewData.setOnClickListener(v -> DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, ProductActivity.class)));
        cardViewPrint = findViewById(R.id.card_print);
        cardViewSettings = findViewById(R.id.card_settings);

    }
}