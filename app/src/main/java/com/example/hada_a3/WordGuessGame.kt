package com.example.hada_a3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

class WordGuessGame {
    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Word Guess Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Button(
                    onClick = { navController.navigate("WordGuessGameOver") },
                ) {
                    Text("Finish Game")
                }
            }
        }
    }
}