package com.example.cashregister.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cashregister.model.Product
import com.example.cashregister.model.Purchase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CashRegisterViewModel : ViewModel() {

    private val _products = mutableStateListOf<Product>()
    val products: List<Product> = _products

    private val _purchaseHistory = mutableStateListOf<Purchase>()
    val purchaseHistory: List<Purchase> = _purchaseHistory

    var selectedIndex by mutableIntStateOf(-1)
        private set

    var enteredQty by mutableStateOf("")
        private set

    var toastMessage by mutableStateOf<String?>(null)
        private set

    // State-based navigation
    var currentScreen by mutableStateOf("main")
        private set

    var selectedHistoryIndex by mutableIntStateOf(0)
        private set

    fun navigateTo(screen: String) {
        currentScreen = screen
    }

    fun viewHistoryDetail(index: Int) {
        selectedHistoryIndex = index
        currentScreen = "history_detail"
    }

    val selectedProductName: String
        get() = if (selectedIndex in _products.indices) _products[selectedIndex].name else "No product selected"

    val displayQty: String
        get() = enteredQty.ifEmpty { "0" }

    val totalText: String
        get() {
            if (selectedIndex < 0 || enteredQty.isEmpty()) return "$0.00"
            val qty = enteredQty.toIntOrNull() ?: 0
            val total = qty * _products[selectedIndex].price
            return "$${String.format("%.2f", total)}"
        }

    init {
        initProducts()
    }

    private fun initProducts() {
        if (_products.isEmpty()) {
            _products.addAll(
                listOf(
                    Product("Ballpoint Pen", 20, 2.40),
                    Product("Highlighter", 12, 2.40),
                    Product("Permanent Marker", 15, 2.90),
                    Product("Mechanical Pencil", 16, 12.90),
                    Product("Glue Stick", 12, 0.90),
                    Product("Sticky Notes", 18, 2.90),
                    Product("Notepad", 30, 3.90),
                    Product("Ruler", 15, 4.90),
                    Product("Lined Notebook", 10, 8.90),
                    Product("Stapler", 8, 7.90)
                )
            )
        }
    }

    fun selectProduct(index: Int) {
        selectedIndex = index
        enteredQty = ""
    }

    fun onNumberClick(number: String) {
        enteredQty += number
    }

    fun onClearClick() {
        enteredQty = ""
    }

    fun onBackspaceClick() {
        if (enteredQty.isNotEmpty()) {
            enteredQty = enteredQty.dropLast(1)
        }
    }

    fun handleBuy() {
        if (selectedIndex < 0) {
            toastMessage = "Please select a product!"
            return
        }
        if (enteredQty.isEmpty()) {
            toastMessage = "Please enter a quantity!"
            return
        }

        val qty = enteredQty.toIntOrNull() ?: 0
        if (qty == 0) {
            toastMessage = "Quantity must be greater than 0!"
            return
        }

        val p = _products[selectedIndex]
        if (qty > p.quantity) {
            toastMessage = "Not enough stock! Only ${p.quantity} available."
            return
        }

        val total = qty * p.price
        _products[selectedIndex] = p.copy(quantity = p.quantity - qty)

        val date = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
        _purchaseHistory.add(Purchase(p.name, qty, total, date))

        toastMessage = "Purchase successful! Total: $${String.format("%.2f", total)}"
        selectedIndex = -1
        enteredQty = ""
    }

    fun handleRestock(restockSelectedIndex: Int, restockQtyText: String): Boolean {
        if (restockSelectedIndex < 0) {
            toastMessage = "Please select a product!"
            return false
        }
        if (restockQtyText.isEmpty()) {
            toastMessage = "Please enter a quantity!"
            return false
        }

        val addQty = restockQtyText.toIntOrNull() ?: 0
        if (addQty <= 0) {
            toastMessage = "Quantity must be greater than 0!"
            return false
        }

        val p = _products[restockSelectedIndex]
        _products[restockSelectedIndex] = p.copy(quantity = p.quantity + addQty)
        toastMessage = "Restocked successfully!"
        return true
    }

    fun clearToast() {
        toastMessage = null
    }
}
