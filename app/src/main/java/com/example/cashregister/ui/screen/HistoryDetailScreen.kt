package com.example.cashregister.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashregister.ui.theme.BlueSlate
import com.example.cashregister.ui.theme.PowderBlush
import com.example.cashregister.ui.theme.VanillaCream
import com.example.cashregister.viewmodel.CashRegisterViewModel

@Composable
fun HistoryDetailScreen(viewModel: CashRegisterViewModel) {
    val purchase = viewModel.purchaseHistory.getOrNull(viewModel.selectedHistoryIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VanillaCream)
            .padding(16.dp)
    ) {
        // Title bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(BlueSlate),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Purchase Detail",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (purchase != null) {
            Text(
                text = "Product: ${purchase.productName}",
                color = BlueSlate,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
            )
            Text(
                text = "Quantity: ${purchase.quantity}",
                color = BlueSlate,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
            Text(
                text = "Total: $${String.format("%.2f", purchase.totalPrice)}",
                color = BlueSlate,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
            Text(
                text = "Date: ${purchase.purchaseDate}",
                color = BlueSlate,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }

        // Spacer to push back button to bottom
        Box(modifier = Modifier.weight(1f))

        // Back button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { viewModel.navigateTo("history_list") },
                colors = ButtonDefaults.buttonColors(containerColor = PowderBlush),
                shape = RectangleShape
            ) {
                Text("Back", color = BlueSlate)
            }
        }
    }
}
