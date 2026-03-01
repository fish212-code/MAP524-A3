package com.example.cashregister;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RestockActivity extends AppCompatActivity {

    int selectedIndex = -1;
    ListView restockList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        restockList = findViewById(R.id.restockListView);
        EditText etQty = findViewById(R.id.etRestockQty);
        Button btnOK = findViewById(R.id.btnOK);
        Button btnCancel = findViewById(R.id.btnCancel);

        refreshList();

        restockList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });

        btnOK.setOnClickListener(v -> {
            if (selectedIndex < 0) {
                Toast.makeText(this, "Please select a product!", Toast.LENGTH_SHORT).show();
                return;
            }

            String qtyText = etQty.getText().toString().trim();
            if (qtyText.isEmpty()) {
                Toast.makeText(this, "Please enter a quantity!", Toast.LENGTH_SHORT).show();
                return;
            }

            int addQty = Integer.parseInt(qtyText);
            if (addQty <= 0) {
                Toast.makeText(this, "Quantity must be greater than 0!", Toast.LENGTH_SHORT).show();
                return;
            }
            AppData.products.get(selectedIndex).quantity += addQty;
            Toast.makeText(this, "Restocked successfully!", Toast.LENGTH_SHORT).show();
            refreshList();
            etQty.setText("");
            selectedIndex = -1;
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    void refreshList() {
        String[] rows = new String[AppData.products.size()];
        for (int i = 0; i < AppData.products.size(); i++) {
            Product p = AppData.products.get(i);
            rows[i] = p.name + "   |   Qty: " + p.quantity;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_single_choice, rows
        );
        restockList.setAdapter(adapter);
        restockList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}
