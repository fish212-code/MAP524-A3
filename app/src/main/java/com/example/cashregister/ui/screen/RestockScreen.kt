package com.example.cashregister.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashregister.ui.theme.BlueSlate
import com.example.cashregister.ui.theme.IcyAqua
import com.example.cashregister.ui.theme.PowderBlush
import com.example.cashregister.ui.theme.VanillaCream
import com.example.cashregister.viewmodel.CashRegisterViewModel

@Composable
fun RestockScreen(viewModel: CashRegisterViewModel, navController: NavController) {
    val context = LocalContext.current
    var selectedIndex by remember { mutableStateOf(-1) }
    var restockQty by remember { mutableStateOf("") }

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
                text = "Restock",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Product list
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            viewModel.products.forEachIndexed { index, product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedIndex == index,
                        onClick = null,
                        colors = RadioButtonDefaults.colors(selectedColor = BlueSlate)
                    )
                    Text(
                        text = "${product.name}   |   Qty: ${product.quantity}",
                        color = BlueSlate,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Quantity
        OutlinedTextField(
            value = restockQty,
            onValueChange = { restockQty = it },
            label = { Text("Add quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // OK & Cancel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 48.dp)
        ) {
            Button(
                onClick = {
                    val (success, msg) = viewModel.handleRestock(selectedIndex, restockQty)
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    if (success) {
                        restockQty = ""
                        selectedIndex = -1
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = IcyAqua),
                shape = RectangleShape,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text("OK", color = BlueSlate)
            }
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = PowderBlush),
                shape = RectangleShape,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text("Cancel", color = BlueSlate)
            }
        }
    }
}
