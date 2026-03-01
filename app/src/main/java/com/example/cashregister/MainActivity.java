package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView productListView;
    TextView tvProductName, tvQuantity, tvTotal;
    Button btnBuy, btnManager;
    int selectedIndex = -1;
    String enteredQty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppData.initProducts();

        productListView = findViewById(R.id.productListView);
        tvProductName = findViewById(R.id.tvProductName);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTotal = findViewById(R.id.tvTotal);
        btnBuy = findViewById(R.id.btnBuy);
        btnManager = findViewById(R.id.btnManager);

        refreshList();

        productListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            Product p = AppData.products.get(position);
            tvProductName.setText(p.name);
            enteredQty = "";
            tvQuantity.setText("0");
            tvTotal.setText("$0.00");
        });

        btnBuy.setOnClickListener(v -> handleBuy());

        btnManager.setOnClickListener(v ->
                startActivity(new Intent(this, ManagerActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    void refreshList() {
        ProductAdapter adapter = new ProductAdapter(this, AppData.products);
        productListView.setAdapter(adapter);
    }

    public void onNumberClick(View view) {
        Button btn = (Button) view;
        enteredQty += btn.getText().toString();
        tvQuantity.setText(enteredQty);
        updateTotal();
    }

    public void onClearClick(View view) {
        enteredQty = "";
        tvQuantity.setText("0");
        tvTotal.setText("$0.00");
    }

    public void onBackspaceClick(View view) {
        if (enteredQty.length() > 0) {
            enteredQty = enteredQty.substring(0, enteredQty.length() - 1);
        }
        tvQuantity.setText(enteredQty.isEmpty() ? "0" : enteredQty);
        updateTotal();
    }

    void updateTotal() {
        if (selectedIndex >= 0 && !enteredQty.isEmpty()) {
            int qty = Integer.parseInt(enteredQty);
            double price = AppData.products.get(selectedIndex).price;
            double total = qty * price;
            tvTotal.setText("$" + String.format("%.2f", total));
        }
    }

    void handleBuy() {
        if (selectedIndex < 0) {
            Toast.makeText(this, "Please select a product!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (enteredQty.isEmpty()) {
            Toast.makeText(this, "Please enter a quantity!", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(enteredQty);

        if (qty == 0) {
            Toast.makeText(this, "Quantity must be greater than 0!", Toast.LENGTH_SHORT).show();
            return;
        }

        Product p = AppData.products.get(selectedIndex);

        if (qty > p.quantity) {
            Toast.makeText(this,
                    "Not enough stock! Only " + p.quantity + " available.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double total = qty * p.price;
        p.quantity -= qty;

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        AppData.purchaseHistory.add(new Purchase(p.name, qty, total, date));

        Toast.makeText(this, "Purchase successful! Total: $" + String.format("%.2f", total),
                Toast.LENGTH_SHORT).show();

        selectedIndex = -1;
        enteredQty = "";
        tvProductName.setText("No product selected");
        tvQuantity.setText("0");
        tvTotal.setText("$0.00");
        refreshList();
    }
}
