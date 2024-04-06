package com.target.bhavesh.ui.screens.details.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.target.bhavesh.ui.screens.details.DetailsViewModel
import com.target.bhavesh.ui.utils.ColorPrimary
import com.target.bhavesh.ui.utils.ErrorScreen
import com.target.bhavesh.ui.utils.HeadingText
import com.target.bhavesh.ui.utils.ProductsDescription
import com.target.bhavesh.ui.utils.ProductsImage
import com.target.bhavesh.ui.utils.ProductsPrice
import com.target.bhavesh.ui.utils.ProductsSubTitle
import com.target.bhavesh.ui.utils.ProductsTitle

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (state.productDetails != null) {
            Scaffold(
                bottomBar = { AddToCartBottomComponent() },
                containerColor = MaterialTheme.colorScheme.background,
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(start = 16.dp, end = 16.dp)
                        .align(TopCenter)
                        .verticalScroll(rememberScrollState())
                ) {
                    val productDetails = state.productDetails ?: return@Column

                    ProductsImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(320.dp),
                        imageUrl = productDetails.imageUrl,
                        id = productDetails.id
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    ProductsTitle(title = productDetails.title, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    ProductsPrice(regularPrice = productDetails.regularPrice, salePrice = productDetails.salePrice)
                    ProductsSubTitle(title = productDetails.fulfillment, color = MaterialTheme.colorScheme.secondary)
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )
                    HeadingText(text = "Product Details")
                    Spacer(modifier = Modifier.height(10.dp))
                    ProductsDescription(title = productDetails.description + productDetails.description, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }

        if (state.error.isNotBlank()) {
            ErrorScreen(state.error) { viewModel.retry() }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AddToCartBottomComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        TextButton(
            onClick = { },
            modifier = Modifier
                .background(color = ColorPrimary, shape = RoundedCornerShape(10.dp))
                .padding(15.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = "Add to Cart",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
            )
        }
    }
}