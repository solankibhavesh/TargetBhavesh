package com.target.bhavesh.ui.navigation

import com.target.bhavesh.util.Constants.DEALS_ID

const val DETAILS_ROUTE = "details_screen"
const val HOME_ROUTE = "home_screen"

sealed class NavScreens(val route: String, val title: String) {
    data object HomeScreen : NavScreens(HOME_ROUTE, "List")
    data object DetailsScreen : NavScreens(DETAILS_ROUTE, "Details")
}

internal fun String?.toolBarTitle(): String {
    return when (this) {
        HOME_ROUTE -> NavScreens.HomeScreen.title
        "$DETAILS_ROUTE/{$DEALS_ID}" -> NavScreens.DetailsScreen.title
        else -> "Target"
    }
}
