package com.example.hada_a3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

class TicTacToe:Game {
    //Initialise scores uses a p2Score variable as it is a 2 player game
    override var playerScore : Int = 0
    var p2Score : Int = 0
    var p1Turn : Boolean = true


    @Composable
    override fun StartScreen(modifier: Modifier, navController: NavController) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Tic Tac Toe Start Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Button(onClick = { navController.navigate("TicTacToe") }) {
                    Text("Start")
                }
            }
        }
    }

    @Composable
    override fun PlayGame(modifier: Modifier, navController: NavController) {
        //Initialise variables
        playerScore = 0
        p2Score = 0
        p1Turn = true

        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier.weight(8.0f)){
                Text("Tic Tac Toe Screen")
            }
            Box(modifier = Modifier.weight(2.0f)){
                Row(){
                    Button(
                        onClick = { navController.navigate("TicTacToeGameOver") },
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