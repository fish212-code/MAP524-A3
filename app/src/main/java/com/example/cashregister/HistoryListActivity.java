package com.example.cashregister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        RecyclerView recyclerView = findViewById(R.id.purchaseRecyclerView);
        Button btnBack = findViewById(R.id.btnBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PurchaseAdapter adapter = new PurchaseAdapter(this, AppData.purchaseHistory, position -> {
            Intent intent = new Intent(this, HistoryDetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        btnBack.setOnClickListener(v -> finish());
    }
}
