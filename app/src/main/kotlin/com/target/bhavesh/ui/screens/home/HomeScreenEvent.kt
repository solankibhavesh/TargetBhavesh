package com.target.bhavesh.ui.screens.home

sealed class HomeScreenEvent {
    data class OnClick(val employeeId: String) : HomeScreenEvent()
}
