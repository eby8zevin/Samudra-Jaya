package com.ahmadabuhasan.skripsi.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadabuhasan.skripsi.R;
import com.ahmadabuhasan.skripsi.data.EditProductActivity;
import com.ahmadabuhasan.skripsi.database.DatabaseAccess;
import com.ahmadabuhasan.skripsi.database.DatabaseOpenHelper;

import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    public Context context;
    public List<HashMap<String, String>> productData;

    public ProductAdapter(Context context1, List<HashMap<String, String>> productData1) {
        this.context = context1;
        this.productData = productData1;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this.context);
        final String product_id = this.productData.get(position).get(DatabaseOpenHelper.PRODUCT_ID);
        databaseAccess.open();
        String currency = databaseAccess.getCurrency();
        holder.textViewProductName.setText((String) this.productData.get(position).get(DatabaseOpenHelper.PRODUCT_ID));
        TextView textView = holder.textViewBuy;
        textView.setText(this.context.getString(R.string.buy) + " : " + currency + ((String) this.productData.get(position).get(DatabaseOpenHelper.PRODUCT_BUY)));
        TextView textView1 = holder.textViewStock;
        textView1.setText(this.context.getString(R.string.stock) + " : " + this.productData.get(position).get(DatabaseOpenHelper.PRODUCT_STOCK));
        TextView textView2 = holder.textViewPrice;
        textView2.setText(this.context.getString(R.string.price) + " : " + currency + ((String) this.productData.get(position).get(DatabaseOpenHelper.PRODUCT_PRICE)));

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(ProductAdapter.this.context).setMessage(R.string.want_to_delete_product)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseAccess.open();
                        if (databaseAccess.deleteProduct(product_id)) {
                            Toasty.error(ProductAdapter.this.context, (int) R.string.product_deleted, Toasty.LENGTH_SHORT).show();
                            ProductAdapter.this.productData.remove(holder.getAdapterPosition());
                            ProductAdapter.this.notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(ProductAdapter.this.context, R.string.failed, Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.productData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewProductName;
        TextView textViewBuy;
        TextView textViewStock;
        TextView textViewPrice;
        ImageView imageViewDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewProductName = itemView.findViewById(R.id.tv_product_name);
            this.textViewBuy = itemView.findViewById(R.id.tv_buy);
            this.textViewStock = itemView.findViewById(R.id.tv_stock);
            this.textViewPrice = itemView.findViewById(R.id.tv_price);
            this.imageViewDelete = itemView.findViewById(R.id.img_delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(ProductAdapter.this.context, EditProductActivity.class);
            i.putExtra(DatabaseOpenHelper.PRODUCT_ID, (String) ((HashMap) ProductAdapter.this.productData.get(getAdapterPosition())).get(DatabaseOpenHelper.PRODUCT_ID));
            ProductAdapter.this.context.startActivity(i);
        }
    }
}