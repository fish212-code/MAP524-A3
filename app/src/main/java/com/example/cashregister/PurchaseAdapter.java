package com.example.cashregister;

import android.content.Context;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    ArrayList<Purchase> purchaseList;
    Context context;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public PurchaseAdapter(Context context, ArrayList<Purchase> list, OnItemClickListener listener) {
        this.context = context;
        this.purchaseList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_purchase, parent, false);
        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        Purchase p = purchaseList.get(position);
        holder.tvName.setText(p.productName);
        holder.tvQty.setText("Qty: " + p.quantity);
        holder.tvTotal.setText("$" + String.format("%.2f", p.totalPrice));
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) listener.onItemClick(pos);
        });
    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public static class PurchaseViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQty, tvTotal;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPurchaseName);
            tvQty = itemView.findViewById(R.id.tvPurchaseQty);
            tvTotal = itemView.findViewById(R.id.tvPurchaseTotal);
        }
    }
}
