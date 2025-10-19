package com.example.hada_a3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

class GameOverScreens {
    @Composable
    fun WordGuessGameOver(modifier: Modifier = Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Word Guess Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Row(){
                    Button(
                        onClick = { navController.navigate("WordStartScreen") },
                    ) {
                        Text("Start again")
                    }
                    Button(onClick = { navController.navigate("MainMenu") }) {
                        Text("Home")
                    }
                }
            }
        }
    }

    @Composable
    fun BirdGameOver(modifier: Modifier = Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Bird Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Row(){
                    Button(
                        onClick = { navController.navigate("BirdStartScreen") },
                    ) {
                        Text("Start again")
                    }
                    Button(onClick = { navController.navigate("MainMenu") }) {
                        Text("Home")
                    }
                }
            }
        }
    }

    @Composable
    fun TicTacToeGameOver(modifier: Modifier = Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Tic Tac Toe Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Row(){
                    Button(
                        onClick = { navController.navigate("TicStartScreen") },
                    ) {
                        Text("Start again")
                    }
                    Button(onClick = { navController.navigate("MainMenu") }) {
                        Text("Home")
                    }
                }
            }
        }
    }
}