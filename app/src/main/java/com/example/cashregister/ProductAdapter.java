package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_product, parent, false);
        }

        Product product = getItem(position);
        if (product == null) return convertView;

        TextView tvName  = convertView.findViewById(R.id.tvItemName);
        TextView tvQty   = convertView.findViewById(R.id.tvItemQty);
        TextView tvPrice = convertView.findViewById(R.id.tvItemPrice);

        tvName.setText(product.getName());
        tvQty.setText(String.valueOf(product.getQuantity()));
        tvPrice.setText(String.format("$%.2f", product.getPrice()));

        return convertView;
    }
}
