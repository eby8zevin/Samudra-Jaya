package com.ahmadabuhasan.samudrajaya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    private final Context context;
    ArrayList<ModelSj> arrayModelSj, dataFilter;
    SearchFilter searchFilter;

    public RecyclerViewAdapter(Context context, ArrayList<ModelSj> arrayModelSj) {
        this.context = context;
        this.arrayModelSj = arrayModelSj;
        this.dataFilter = arrayModelSj;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        ModelSj modelSj = arrayModelSj.get(position);

        TextView textView = holder.NamaBarang;
        textView.setText(this.context.getString(R.string.namabarang) + " : " + modelSj.getNamaBarang());

        TextView textView1 = holder.KodeBarang;
        textView1.setText(this.context.getString(R.string.kodebarang) + " : " + modelSj.getKodeBarang());

        TextView textView2 = holder.Kategori;
        textView2.setText(this.context.getString(R.string.kategori) + " : " + modelSj.getKategori());

        TextView textView3 = holder.Beli;
        textView3.setText(this.context.getString(R.string.beli) + " : Rp " + NumberFormat.getInstance().format(modelSj.getBeli()));

        TextView textView4 = holder.Stok;
        textView4.setText(this.context.getString(R.string.stok) + " : " + modelSj.getStok());
        textView4.setTextColor(Color.RED);

        TextView textView5 = holder.Harga;
        textView5.setText(this.context.getString(R.string.harga) + " : Rp " + NumberFormat.getInstance().format(modelSj.getHarga()));

        TextView textView6 = holder.JumlahQTY;
        textView6.setText(this.context.getString(R.string.jumlahqty) + " : " + modelSj.getJumlahQTY());

        TextView textView7 = holder.DiscQTY;
        textView7.setText(this.context.getString(R.string.discqty) + " : " + modelSj.getDiscQTY());

        TextView textView8 = holder.Satuan;
        textView8.setText(this.context.getString(R.string.satuan) + " : " + modelSj.getSatuan());

        TextView textView9 = holder.LastUpdate;
        textView9.setText(this.context.getString(R.string.lastupdate) + " : " + modelSj.getLastUpdate());

        TextView textView10 = holder.Keterangan;
        textView10.setText(this.context.getString(R.string.keterangan) + ": " + modelSj.getKeterangan());

        TextView textView11 = holder.Suplier;
        textView11.setText(this.context.getString(R.string.suplier) + " : " + modelSj.getSuplier());
    }

    @Override
    public int getItemCount() {
        return arrayModelSj.size();
    }

    @Override
    public Filter getFilter() {
        if (searchFilter == null) {
            searchFilter = new SearchFilter(dataFilter, this);
        }
        return searchFilter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NamaBarang;
        TextView KodeBarang;
        TextView Kategori;
        TextView Beli;
        TextView Stok;
        TextView Harga;
        TextView JumlahQTY;
        TextView DiscQTY;
        TextView Satuan;
        TextView LastUpdate;
        TextView Keterangan;
        TextView Suplier;

        public MyViewHolder(View itemView) {
            super(itemView);
            NamaBarang = itemView.findViewById(R.id.tv_NamaBarang);
            KodeBarang = itemView.findViewById(R.id.tv_KodeBarang);
            Kategori = itemView.findViewById(R.id.tv_Kategori);
            Beli = itemView.findViewById(R.id.tv_Beli);
            Stok = itemView.findViewById(R.id.tv_Stok);
            Harga = itemView.findViewById(R.id.tv_Harga);
            JumlahQTY = itemView.findViewById(R.id.tv_JumlahQTY);
            DiscQTY = itemView.findViewById(R.id.tv_DiscQTY);
            Satuan = itemView.findViewById(R.id.tv_Satuan);
            LastUpdate = itemView.findViewById(R.id.tv_LastUpdate);
            Keterangan = itemView.findViewById(R.id.tv_Keterangan);
            Suplier = itemView.findViewById(R.id.tv_Suplier);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
                    final int position = getAdapterPosition();
                    final ModelSj modelSj = arrayModelSj.get(position);

                    final EditText input_stock = new EditText(context);

                    input_stock.setInputType(InputType.TYPE_CLASS_NUMBER);
                    input_stock.setHint("Update Stok Baru");

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(input_stock);
                    builder.setTitle(modelSj.getNamaBarang());
                    builder.setMessage("Stok lama: " + modelSj.getStok());
                    builder.setCancelable(false);
                    builder.setNeutralButton(
                            "Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int edit_id = modelSj.getNo();
                                    String up_stock = input_stock.getText().toString();

                                    if (up_stock.isEmpty()) {
                                        Toast.makeText(context, "Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                                    } else {
                                        updateData(edit_id, up_stock);
                                    }
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    Button btnNeutral = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                    btnNeutral.setBackgroundColor(Color.RED);
                    btnNeutral.setTextColor(Color.WHITE);

                    Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    btnPositive.setBackgroundColor(Color.BLACK);
                    btnPositive.setTextColor(Color.WHITE);
                }
            });
        }
    }

    private void updateData(int id, String stock) {
        String url = ApiClient.URL_UPDATE;

        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {

            ResponStatus responStatus = new Gson().fromJson(response, ResponStatus.class);
            int status_kode = responStatus.getStatus_kode();
            String status_pesan = responStatus.getStatus_pesan();

            if (status_kode == 1) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
                MainActivity.mInstance.getData();

            } else if (status_kode == 2) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 3) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 4) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 5) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 6) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 7) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 8) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 9) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else if (status_kode == 10) {
                Toast.makeText(context, status_pesan, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Status Kesalahan Tidak Diketahui!", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // jika respon tidak ditemukan maka akan menampilkan berbagai error berikut ini
            if (error instanceof TimeoutError) {
                Toast.makeText(context, "Network TimeoutError", Toast.LENGTH_SHORT).show();
            } else if (error instanceof NoConnectionError) {
                Toast.makeText(context, "Nerwork NoConnectionError", Toast.LENGTH_SHORT).show();
            } else if (error instanceof AuthFailureError) {
                Toast.makeText(context, "Network AuthFailureError", Toast.LENGTH_SHORT).show();
            } else if (error instanceof ServerError) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            } else if (error instanceof NetworkError) {
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            } else if (error instanceof ParseError) {
                Toast.makeText(context, "Parse Error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Status Error Tidak Diketahui!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", String.valueOf(id));
                hashMap.put("stock", stock);

                return hashMap;
            }
        };

        AppController.getInstance().addToQueue(request, "update_data");
    }
}