package com.target.bhavesh.ui.screens.home.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.target.bhavesh.ui.screens.home.HomeViewModel
import com.target.bhavesh.ui.navigation.NavScreens
import com.target.bhavesh.ui.utils.ErrorScreen

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(state.products, key = { _, product -> product.id }) { index, product ->
                if (index > 0) HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                ProductsListRow(
                    product = product,
                    onItemClick = { navController.navigate(NavScreens.DetailsScreen.route + "/${it.id}") }
                )
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