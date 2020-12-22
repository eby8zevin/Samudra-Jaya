package com.ahmadabuhasan.skripsi.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmadabuhasan.skripsi.R;
import com.ahmadabuhasan.skripsi.adapter.ProductAdapter;
import com.ahmadabuhasan.skripsi.database.DatabaseAccess;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class ProductActivity extends AppCompatActivity {

    EditText editTextSearch;
    ImageView imgNoProduct;
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
        this.imgNoProduct = findViewById(R.id.image_no_product);

        this.floatingActionButton = findViewById(R.id.fab_add);
        this.floatingActionButton.setOnClickListener(v -> ProductActivity.this.startActivity(new Intent(ProductActivity.this, AddProductActivity.class)));

        this.recyclerView = findViewById(R.id.product_recyclerview);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerView.setHasFixedSize(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<HashMap<String, String>> productData = databaseAccess.getProducts();
        Log.d("data", "" + productData.size());
        if (productData.size() <= 0) {
            Toasty.info(this, R.string.no_product_found, Toasty.LENGTH_SHORT).show();
            this.imgNoProduct.setImageResource(R.drawable.no_data);
        } else {
            this.imgNoProduct.setVisibility(View.GONE);
            ProductAdapter productAdapter2 = new ProductAdapter(this, productData);
            this.productAdapter = productAdapter2;
            this.recyclerView.setAdapter(productAdapter2);
        }
    }
}