package com.example.cashregister.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.cashregister.ui.theme.IcyAqua
import com.example.cashregister.ui.theme.LightBlue
import com.example.cashregister.ui.theme.PowderBlush
import com.example.cashregister.ui.theme.VanillaCream

@Composable
fun ManagerScreen(navController: NavController) {
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
                text = "Manager Panel",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Center content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // History
            Button(
                onClick = { navController.navigate("history_list") },
                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                shape = RectangleShape,
                modifier = Modifier
                    .padding(top = 60.dp)
                    .width(200.dp)
                    .height(60.dp)
            ) {
                Text("History", color = BlueSlate, fontSize = 18.sp)
            }

            // Restock
            Button(
                onClick = { navController.navigate("restock") },
                colors = ButtonDefaults.buttonColors(containerColor = IcyAqua),
                shape = RectangleShape,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(200.dp)
                    .height(60.dp)
            ) {
                Text("Restock", color = BlueSlate, fontSize = 18.sp)
            }
        }

        // Back
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate("main") },
                colors = ButtonDefaults.buttonColors(containerColor = PowderBlush),
                shape = RectangleShape
            ) {
                Text("Back", color = BlueSlate)
            }
        }
    }
}
