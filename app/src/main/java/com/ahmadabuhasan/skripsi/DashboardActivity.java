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

        this.cardViewKaca = findViewById(R.id.card_kaca);
        this.cardViewPigura = findViewById(R.id.card_pigura);
        this.cardViewKasir = findViewById(R.id.card_kasir);
        this.cardViewData = findViewById(R.id.card_data);
        this.cardViewData.setOnClickListener(v -> DashboardActivity.this.startActivity(new Intent(DashboardActivity.this, ProductActivity.class)));
        this.cardViewPrint = findViewById(R.id.card_print);
        this.cardViewSettings = findViewById(R.id.card_settings);
    }
}