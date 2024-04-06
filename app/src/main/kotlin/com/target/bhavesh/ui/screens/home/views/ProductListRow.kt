package com.target.bhavesh.ui.screens.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.target.bhavesh.domain.models.Product
import com.target.bhavesh.ui.utils.ProductsImage
import com.target.bhavesh.ui.utils.ProductsPrice
import com.target.bhavesh.ui.utils.ProductsSubTitle
import com.target.bhavesh.ui.utils.ProductsTitle

@Composable
fun ProductsListRow(product: Product, onItemClick: (Product) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(product) }
            .padding(10.dp)
    ) {
        ProductsImage(
            modifier = Modifier.size(140.dp, 140.dp),
            imageUrl = product.imageUrl,
            id = product.id
        )

        Column(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 5.dp)
        ) {
            ProductsPrice(regularPrice = product.regularPrice, salePrice = product.salePrice)
            ProductsSubTitle(title = product.fulfillment, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(5.dp))
            ProductsTitle(product.title)
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                ProductsSubTitle(title = product.availability, color = Color.Green)
                Spacer(modifier = Modifier.width(5.dp))
                ProductsSubTitle(title = "in aisle ${product.aisle}", color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}