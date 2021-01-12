package com.ahmadabuhasan.samudrajaya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

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
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}