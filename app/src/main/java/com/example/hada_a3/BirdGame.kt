package com.example.hada_a3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

class BirdGame:Game {
    //Initialise scores
    override var playerScore : Int = 0

    @Composable
    override fun StartScreen(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Bird Start Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Button(onClick = { navController.navigate("BirdGame") }) {
                    Text("Start")
                }
            }
        }
    }

    @Composable
    override fun PlayGame(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Bird Game Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Row(){
                    Button(
                        onClick = { navController.navigate("BirdGameOver") },
                    ) {
                        Text("Finish Game")
                    }
                }
            }
        }
    }

    @Composable
    override fun GameOver(modifier: Modifier, navController: NavController) {
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
}