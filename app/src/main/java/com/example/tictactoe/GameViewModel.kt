package com.example.tictactoe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState

    fun makeMove(position: Int) {
        _uiState.value = GameLogic.makeMove(_uiState.value, position)
    }

    fun resetGame() {
        _uiState.value = GameUiState()
    }

    fun sendOnlineMove(position: Int) {
        OnlineGameRepository.sendMove(position)
    }
}

data class GameUiState(
    val board: List<Int> = List(9) { 0 },
    val currentPlayer: Int = 1,
    val winner: Int? = null,
    val online: Boolean = false
)
