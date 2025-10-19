package com.example.hada_a3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

class StartScreens {

    @Composable
    fun WordStartScreen(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Word Guess Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Button(onClick = { navController.navigate("WordGuessGame") }) {
                    Text("Start")
                }
            }
        }
    }

    @Composable
    fun BirdStartScreen(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Word Guess Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Button(onClick = { navController.navigate("BirdGame") }) {
                    Text("Start")
                }
            }
        }
    }

    @Composable
    fun TicStartScreen(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Word Guess Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Button(onClick = { navController.navigate("TicTacToe") }) {
                    Text("Start")
                }
            }
        }
    }
}