package com.example.hada_a3

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

class TicTacToe:Game {
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
        var gameGrid by rememberSaveable { mutableStateOf(Array(9) { 0 }) }
        var p1Turn by rememberSaveable { mutableStateOf(true) }
        var playerScore by rememberSaveable { mutableIntStateOf(0) }
        var p2Score by rememberSaveable { mutableIntStateOf(0) }
        var gameOver by rememberSaveable { mutableStateOf(false) }
        var totalTurns by rememberSaveable { mutableIntStateOf(0) }

        //111   000   000
        //000   111   000
        //000   000   111

        //100   010   001
        //100   010   001
        //100   010   001

        //100   001
        //010   010
        //001   100

        //Winning states for either player where 1 is their tile and 0 is not theirs or empty as above
        val winningStates = listOf(
            //Horizontal wins
            listOf(1, 1, 1, 0, 0, 0, 0 ,0 ,0),
            listOf(0, 0, 0, 1, 1, 1, 0 ,0 ,0),
            listOf(0, 0, 0, 0 ,0 ,0, 1, 1, 1),
            //Vertical wins
            listOf(1, 0, 0, 1, 0, 0, 1 ,0, 0),
            listOf(0, 1, 0, 0, 1, 0, 0, 1, 0),
            listOf(0, 0, 1, 0, 0, 1, 0, 0, 1),
            //Diagonal wins
            listOf(1, 0, 0, 0, 1, 0, 0, 0, 1),
            listOf(0, 0, 1, 0, 1, 0, 1, 0, 0)
        )

        //Column to hold the UI of the game
        Column(
            modifier = modifier
        ){
            //Box to display the score and which player's turn it is
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize()
            ){
                Row(){
                    //Display player 1's score
                    Text(
                        "Player 1 Score: $playerScore",
                        modifier = Modifier
                            .weight(0.5f)
                            .align(CenterVertically),
                        textAlign = TextAlign.Start
                    )

                    //Display which player's turn it is
                    var playerTurnString = "X's Turn"
                    if(p1Turn) { playerTurnString = "X's Turn" }
                    else{ playerTurnString = "O's Turn" }
                    Text(
                        playerTurnString,
                        modifier = Modifier
                            .weight(0.5f)
                            .align(CenterVertically),
                        textAlign = TextAlign.Center
                    )

                    //Display player 2's score
                    Text(
                        "Player 2 Score: $p2Score",
                        modifier = Modifier
                            .weight(0.5f)
                            .align(CenterVertically),
                        textAlign = TextAlign.End
                    )
                }
            }

            //Row to hold the game itself
            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(80.dp)
            ){
                //Draw the grid
                Column(){
                    for (i in 0..2) {
                        //Rows of buttons
                        Row(
                            Modifier
                                .align(Alignment.CenterHorizontally)
                                .weight(0.1f)
                        ) {
                            for (j in 0..2) {
                                //Calculate the index of the box
                                val index = i*3+j
                                //Draw each box of the grid and define the onclick
                                Box(
                                    modifier = Modifier
                                        .border(2.dp, Color.White, RectangleShape)
                                        .width(100.dp)
                                        .height(100.dp)
                                        .align(Alignment.CenterVertically)
                                        .weight(0.33f)
                                        .clickable {
                                            //If the tile has not already been changed
                                            if(gameGrid[index] == 0){
                                                //If it is player 1's turn
                                                if(p1Turn){ gameGrid[index] = 1}
                                                else{ gameGrid[index] = 2 }
                                                //Alternate which player's turn it is
                                                p1Turn = !p1Turn
                                                //Increment total turns
                                                totalTurns++
                                                //Check if the board is full
                                                if(totalTurns == 9) { gameOver = true }
                                                //Otherwise check if one of the players has won
                                                else{

                                                }
                                            }
                                        }
                                ) {
                                    when (gameGrid[index]) {
                                        //Set the contents of the grid cell when clicked
                                        1 -> Icon(
                                            Icons.Default.Close,
                                            contentDescription = "X",
                                            modifier = Modifier.fillMaxSize()
                                            )
                                        2 -> Icon(
                                            Icons.Default.Face,
                                            contentDescription = "O",
                                            modifier = Modifier.fillMaxSize()
                                            )
                                        else -> Color.Transparent
                                    }
                                }
                            }
                        }
                    }
                }
            }


            //Box for finish game - need to remove
            Box(
                modifier = Modifier
                    .weight(0.3f)
            ){
                Button(
                    onClick = { navController.navigate("TicTacToeGameOver")},
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ){
                    Text("Finish Game")
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