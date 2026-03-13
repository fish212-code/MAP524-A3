package com.example.cashregister.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavController
import com.example.cashregister.ui.theme.BlueSlate
import com.example.cashregister.ui.theme.PowderBlush
import com.example.cashregister.ui.theme.VanillaCream
import com.example.cashregister.viewmodel.CashRegisterViewModel

@Composable
fun HistoryListScreen(viewModel: CashRegisterViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VanillaCream)
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
                text = "Purchase History",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Purchase list
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            viewModel.purchaseHistory.forEachIndexed { index, purchase ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(VanillaCream)
                        .clickable {
                            viewModel.selectHistoryItem(index)
                            navController.navigate("history_detail")
                        }
                        .padding(12.dp)
                ) {
                    Text(
                        text = purchase.productName,
                        color = BlueSlate,
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Qty: ${purchase.quantity}",
                        color = BlueSlate,
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$${String.format("%.2f", purchase.totalPrice)}",
                        color = BlueSlate,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = PowderBlush),
                shape = RectangleShape
            ) {
                Text("Back", color = BlueSlate)
            }
        }
    }
}
