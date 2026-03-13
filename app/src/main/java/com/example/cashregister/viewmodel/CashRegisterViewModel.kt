package com.example.cashregister.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cashregister.model.Product
import com.example.cashregister.model.Purchase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CashRegisterViewModel : ViewModel() {

    var products by mutableStateOf(
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
        private set

    var purchaseHistory by mutableStateOf(listOf<Purchase>())
        private set

    var selectedIndex by mutableStateOf(-1)
        private set

    var enteredQty by mutableStateOf("")
        private set

    var selectedHistoryIndex by mutableStateOf(-1)
        private set

    val selectedProductName: String
        get() = if (selectedIndex in products.indices) products[selectedIndex].name else "No product selected"

    val displayQty: String
        get() = enteredQty.ifEmpty { "0" }

    val totalText: String
        get() {
            if (selectedIndex < 0 || enteredQty.isEmpty()) return "$0.00"
            val qty = enteredQty.toIntOrNull() ?: 0
            val total = qty * products[selectedIndex].price
            return "$${String.format("%.2f", total)}"
        }

    fun selectProduct(index: Int) {
        selectedIndex = index
        enteredQty = ""
    }

    fun selectHistoryItem(index: Int) {
        selectedHistoryIndex = index
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

    fun handleBuy(): String {
        if (selectedIndex < 0) {
            return "Please select a product!"
        }
        if (enteredQty.isEmpty()) {
            return "Please enter a quantity!"
        }

        val qty = enteredQty.toIntOrNull() ?: 0
        if (qty == 0) {
            return "Quantity must be greater than 0!"
        }

        val p = products[selectedIndex]
        if (qty > p.quantity) {
            return "Not enough stock! Only ${p.quantity} available."
        }

        val total = qty * p.price
        products = products.toMutableList().also {
            it[selectedIndex] = p.copy(quantity = p.quantity - qty)
        }

        val date = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
        purchaseHistory = purchaseHistory + Purchase(p.name, qty, total, date)

        val msg = "Purchase successful! Total: $${String.format("%.2f", total)}"
        selectedIndex = -1
        enteredQty = ""
        return msg
    }

    fun handleRestock(restockSelectedIndex: Int, restockQtyText: String): Pair<Boolean, String> {
        if (restockSelectedIndex < 0) {
            return Pair(false, "Please select a product!")
        }
        if (restockQtyText.isEmpty()) {
            return Pair(false, "Please enter a quantity!")
        }

        val addQty = restockQtyText.toIntOrNull() ?: 0
        if (addQty <= 0) {
            return Pair(false, "Quantity must be greater than 0!")
        }

        val p = products[restockSelectedIndex]
        products = products.toMutableList().also {
            it[restockSelectedIndex] = p.copy(quantity = p.quantity + addQty)
        }
        return Pair(true, "Restocked successfully!")
    }
}
