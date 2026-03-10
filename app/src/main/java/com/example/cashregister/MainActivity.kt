package com.example.cashregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cashregister.ui.screen.HistoryDetailScreen
import com.example.cashregister.ui.screen.HistoryListScreen
import com.example.cashregister.ui.screen.MainScreen
import com.example.cashregister.ui.screen.ManagerScreen
import com.example.cashregister.ui.screen.RestockScreen
import com.example.cashregister.ui.theme.CashRegisterTheme
import com.example.cashregister.viewmodel.CashRegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashRegisterTheme {
                val viewModel: CashRegisterViewModel = viewModel()

                Box(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                    when (viewModel.currentScreen) {
                        "main" -> MainScreen(viewModel = viewModel)
                        "manager" -> ManagerScreen(viewModel = viewModel)
                        "restock" -> RestockScreen(viewModel = viewModel)
                        "history_list" -> HistoryListScreen(viewModel = viewModel)
                        "history_detail" -> HistoryDetailScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
