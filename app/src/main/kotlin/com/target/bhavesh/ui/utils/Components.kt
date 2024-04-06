package com.target.bhavesh.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.target.bhavesh.domain.models.ProductPrice
import com.target.bhavesh.ui.navigation.HOME_ROUTE
import com.target.bhavesh.ui.navigation.toolBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(navController: NavHostController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = navController.currentBackStackEntryAsState().value?.destination?.route.toolBarTitle(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            )
        },
        modifier = Modifier
            .background(ColorPrimary)
            .shadow(elevation = 10.dp),
        navigationIcon = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route != HOME_ROUTE) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                }
            }
        },
    )
}

@Composable
fun ProductsImage(modifier: Modifier, imageUrl: String?, id: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = "Product Image: $id",
        modifier = modifier
    )
}

@Composable
fun HeadingText(text: String, fontSize: TextUnit = 16.sp) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProductsTitle(title: String, fontSize: TextUnit = 14.sp) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start,
        fontSize = fontSize,
        lineHeight = 18.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ProductsPrice(regularPrice: ProductPrice, salePrice: ProductPrice?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = salePrice?.displayPrice ?: regularPrice.displayPrice,
            color = ColorPrimary,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 18.sp,
        )
        Text(
            text = "reg. ${regularPrice.displayPrice}",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Composable
fun ProductsSubTitle(title: String, color: Color, fontSize: TextUnit = 12.sp) {
    Text(
        text = title,
        color = color,
        textAlign = TextAlign.Start,
        fontSize = fontSize,
        maxLines = 1,
        lineHeight = 18.sp,
    )
}

@Composable
fun ProductsDescription(title: String, color: Color, fontSize: TextUnit = 14.sp) {
    Text(
        text = title,
        color = color,
        textAlign = TextAlign.Start,
        fontSize = fontSize,
        lineHeight = 18.sp,
    )
}

@Composable
fun BoxScope.ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center)
            .padding(16.dp)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onRetry.invoke() }) {
            Text(text = "Retry")
        }
    }
}