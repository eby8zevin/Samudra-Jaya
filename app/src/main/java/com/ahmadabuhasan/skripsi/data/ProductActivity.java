package com.ahmadabuhasan.skripsi.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ahmadabuhasan.skripsi.R;
import com.ahmadabuhasan.skripsi.adapter.ProductAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {

    EditText editTextSearch;
    FloatingActionButton floatingActionButton;
    ProgressDialog loading;
    ProductAdapter productAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.all_product);

        this.editTextSearch = findViewById(R.id.et_search);

        this.floatingActionButton = findViewById(R.id.fab_add);
        this.floatingActionButton.setOnClickListener(v -> ProductActivity.this.startActivity(new Intent(ProductActivity.this, AddProductActivity.class)));

        this.recyclerView = findViewById(R.id.product_recyclerview);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerView.setHasFixedSize(true);
    }
}