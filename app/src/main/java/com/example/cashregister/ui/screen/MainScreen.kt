package com.example.cashregister.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashregister.model.Product
import com.example.cashregister.ui.theme.BlueSlate
import com.example.cashregister.ui.theme.IcyAqua
import com.example.cashregister.ui.theme.LightBlue
import com.example.cashregister.ui.theme.PowderBlush
import com.example.cashregister.ui.theme.VanillaCream
import com.example.cashregister.viewmodel.CashRegisterViewModel

@Composable
fun MainScreen(viewModel: CashRegisterViewModel, navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VanillaCream)
    ) {
        // Top bar
        TopBar(onManagerClick = { navController.navigate("manager") })

        if (isLandscape) {
            LandscapeContent(viewModel = viewModel)
        } else {
            PortraitContent(viewModel = viewModel)
        }
    }
}

@Composable
private fun TopBar(onManagerClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(BlueSlate),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Cash Register",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Button(
            onClick = onManagerClick,
            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
            shape = RectangleShape,
            modifier = Modifier
                .padding(end = 8.dp)
                .height(36.dp)
        ) {
            Text("Manager", color = BlueSlate, fontSize = 12.sp)
        }
    }
}

@Composable
private fun PortraitContent(viewModel: CashRegisterViewModel) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        // Info
        InfoSection(viewModel = viewModel)

        // Numpad
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        ) {
            Numpad(
                viewModel = viewModel,
                modifier = Modifier.weight(1f)
            )
            BuyButton(
                onClick = {
                    val msg = viewModel.handleBuy()
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .width(64.dp)
                    .fillMaxHeight()
                    .padding(start = 4.dp)
            )
        }

        // Products
        Text(
            text = "Products",
            color = BlueSlate,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
        )

        // Product list
        ProductList(
            products = viewModel.products,
            selectedIndex = viewModel.selectedIndex,
            onItemClick = { viewModel.selectProduct(it) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
private fun LandscapeContent(viewModel: CashRegisterViewModel) {
    val context = LocalContext.current

    Row(modifier = Modifier.fillMaxSize()) {
        // Left: Product Lst
        ProductList(
            products = viewModel.products,
            selectedIndex = viewModel.selectedIndex,
            onItemClick = { viewModel.selectProduct(it) },
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .padding(8.dp)
        )

        // Divider
        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            thickness = 1.dp,
            color = IcyAqua
        )

        // Right: Controls
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            // Info
            InfoSection(viewModel = viewModel)

            // Numpad
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 8.dp)
            ) {
                Numpad(
                    viewModel = viewModel,
                    modifier = Modifier.weight(1f)
                )
                BuyButton(
                    onClick = {
                        val msg = viewModel.handleBuy()
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .width(64.dp)
                        .fillMaxHeight()
                        .padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun InfoSection(viewModel: CashRegisterViewModel) {
    Column(modifier = Modifier.padding(8.dp)) {
        // Product name
        Text(
            text = viewModel.selectedProductName,
            color = BlueSlate,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = viewModel.displayQty,
                color = BlueSlate,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
                    .padding(8.dp)
            )
            Box(modifier = Modifier.width(4.dp))
            Text(
                text = viewModel.totalText,
                color = BlueSlate,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
private fun Numpad(viewModel: CashRegisterViewModel, modifier: Modifier = Modifier) {
    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("C", "0", "\u232B")
    )

    Column(modifier = modifier) {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { key ->
                    val bgColor = when (key) {
                        "C", "\u232B" -> PowderBlush
                        else -> IcyAqua
                    }
                    Button(
                        onClick = {
                            when (key) {
                                "C" -> viewModel.onClearClick()
                                "\u232B" -> viewModel.onBackspaceClick()
                                else -> viewModel.onNumberClick(key)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
                        shape = RectangleShape,
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp)
                    ) {
                        Text(key, color = BlueSlate, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun BuyButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = PowderBlush),
        shape = RectangleShape,
        modifier = modifier
    ) {
        Text("BUY", color = BlueSlate, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
private fun ProductList(
    products: List<Product>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(products) { index, product ->
            ProductItem(
                product = product,
                isSelected = index == selectedIndex,
                onClick = { onItemClick(index) }
            )
            if (index < products.lastIndex) {
                HorizontalDivider(thickness = 1.dp, color = IcyAqua)
            }
        }
    }
}

@Composable
private fun ProductItem(product: Product, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) IcyAqua else VanillaCream)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Text(
            text = product.name,
            color = BlueSlate,
            fontSize = 14.sp,
            modifier = Modifier.weight(3f)
        )
        Text(
            text = "${product.quantity}",
            color = BlueSlate,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$${String.format("%.2f", product.price)}",
            color = BlueSlate,
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}
