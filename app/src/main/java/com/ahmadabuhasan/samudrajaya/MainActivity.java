package com.ahmadabuhasan.samudrajaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Ahmad Abu Hasan on 12/01/2021
 * "com.android.tools.build:gradle:4.1.1"
 */

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static MainActivity mInstance;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<ModelSj> arrayModalSj;
    private static long back_pressed;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatingActionButton = findViewById(R.id.ic_add);

        mInstance = this;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        getData();
    }

    public void getData() {
        String url = ApiClient.URL_READ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Type typeModelSj = new TypeToken<ArrayList<ModelSj>>() {
                    }.getType();

                    arrayModalSj = new Gson().fromJson(response, typeModelSj);
                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, arrayModalSj);
                    recyclerView.setAdapter(recyclerViewAdapter);
                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(MainActivity.this, "Network TimeoutError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(MainActivity.this, "Nerwork NoConnectionError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(MainActivity.this, "Network AuthFailureError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(MainActivity.this, "Parse Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Status Kesalahan Tidak Diketahui!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppController.getInstance().addToQueue(stringRequest, "data");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem menuItem = menu.findItem(R.id.action_search);

        editText = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setQueryHint("Cari Nama / Kode . . .");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerViewAdapter.getFilter().filter(query);
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        menuItem.getIcon().setVisible(false, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.barcode) {
            Intent i = new Intent(MainActivity.this, BarcodeActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.refresh) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(getApplicationContext(), "Tekan Sekali Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}