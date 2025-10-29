package com.example.tictactoe

object GameLogic {

    fun makeMove(state: GameUiState, position: Int): GameUiState {
        if (state.board[position] != 0 || state.winner != null) return state

        val newBoard = state.board.toMutableList()
        newBoard[position] = state.currentPlayer

        val winner = checkWinner(newBoard)
        val nextPlayer = if (state.currentPlayer == 1) 2 else 1

        return state.copy(board = newBoard, currentPlayer = nextPlayer, winner = winner)
    }

    private fun checkWinner(board: List<Int>): Int? {
        val lines = listOf(
            listOf(0,1,2), listOf(3,4,5), listOf(6,7,8),
            listOf(0,3,6), listOf(1,4,7), listOf(2,5,8),
            listOf(0,4,8), listOf(2,4,6)
        )
        for (line in lines) {
            val (a,b,c) = line
            if (board[a] != 0 && board[a] == board[b] && board[a] == board[c]) {
                return board[a]
            }
        }
        return null
    }

    fun aiMove(state: GameUiState): Int {
        val empty = state.board.mapIndexed { i, v -> i to v }.filter { it.second == 0 }
        return empty.random().first
    }
}

