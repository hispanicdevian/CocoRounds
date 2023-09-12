package com.example.cocorounds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CocoRoundsViewModel : ViewModel() {
    private val _roundInputData = MutableStateFlow(RoundInputData(0, 0))
    val roundInputData = _roundInputData // Expose as read-only StateFlow

    private val _restInputData = MutableStateFlow(RestInputData(0, 0))
    val restInputData = _restInputData // Expose as read-only StateFlow

    // New property to represent the number of rounds as an Int
    private val _numberOfRounds = MutableStateFlow(0)
    val numberOfRounds = _numberOfRounds // Expose as read-only StateFlow

    fun updateNumberOfRounds(number: Int) {
        viewModelScope.launch {
            _numberOfRounds.value = number
        }
    }

    fun updateRoundInput(minutes: Int, seconds: Int) {
        viewModelScope.launch {
            _roundInputData.value = RoundInputData(minutes, seconds)
        }
    }
    fun updateRestInput(minutes: Int, seconds: Int) {
        viewModelScope.launch {
            _restInputData.value = RestInputData(minutes, seconds)
        }
    }
    // Function to handle the "Ready" button click

    fun onReadyButtonClick(navController: NavHostController) {
        // Perform any necessary logic before navigating
        navController.navigate("five_second_screen")
    }

    // Function to handle the "Clear" button click
    fun onClearButtonClick() {
        clearInputData()
    }

    private fun clearInputData() {
        viewModelScope.launch {
            _roundInputData.value = RoundInputData(0, 0)
            _restInputData.value = RestInputData(0, 0)
            _numberOfRounds.value = 0
        }
    }
}