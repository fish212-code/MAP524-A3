package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnRestock = findViewById(R.id.btnRestock);
        Button btnBack = findViewById(R.id.btnBack);

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryListActivity.class))
        );

        btnRestock.setOnClickListener(v ->
                startActivity(new Intent(this, RestockActivity.class))
        );

        btnBack.setOnClickListener(v -> finish());
    }
}
