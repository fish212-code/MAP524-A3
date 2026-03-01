package com.example.cashregister;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        int position = getIntent().getIntExtra("position", 0);
        Purchase p = AppData.purchaseHistory.get(position);

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvQty = findViewById(R.id.tvDetailQty);
        TextView tvTotal = findViewById(R.id.tvDetailTotal);
        TextView tvDate = findViewById(R.id.tvDetailDate);
        Button btnBack = findViewById(R.id.btnBack);

        tvName.setText("Product: " + p.productName);
        tvQty.setText("Quantity: " + p.quantity);
        tvTotal.setText("Total: $" + String.format("%.2f", p.totalPrice));
        tvDate.setText("Date: " + p.purchaseDate);

        btnBack.setOnClickListener(v -> finish());
    }
}
