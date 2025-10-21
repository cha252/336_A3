package com.example.hada_a3

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

class TicTacToe{
    //Function to check if a game state is a winning game state or not
    fun checkWin(winningStates: Array<Array<Int>>, playerGrid: Array<Int>): Boolean {
        return winningStates.any { winningState ->
            winningState.indices.all { i -> winningState[i] == 0 || playerGrid[i] == 1 }
        }
    }

    @Composable fun PlayGame(modifier: Modifier, navController: NavController) {
        //Initialise variables
        var gameGrid by rememberSaveable { mutableStateOf(Array(9) { 0 }) }
        var p1Grid by rememberSaveable { mutableStateOf(Array(9) { 0 }) }
        var p2Grid by rememberSaveable { mutableStateOf(Array(9) { 0 }) }
        var p1Turn by rememberSaveable { mutableStateOf(true) }
        var p1Score by rememberSaveable{ mutableIntStateOf(0) }
        var p2Score by rememberSaveable{ mutableIntStateOf(0) }
        var totalTurns by rememberSaveable { mutableIntStateOf(0) }
        var p1Win by rememberSaveable { mutableStateOf(false) }
        var p2Win by rememberSaveable { mutableStateOf(false) }
        var draw by rememberSaveable { mutableStateOf(false) }

        //Winning states for either player where 1 is their tile and 0 is  anything else
        val winningStates = arrayOf(
            //Horizontal wins
            arrayOf(1, 1, 1, 0, 0, 0, 0 ,0 ,0),
            arrayOf(0, 0, 0, 1, 1, 1, 0 ,0 ,0),
            arrayOf(0, 0, 0, 0 ,0 ,0, 1, 1, 1),
            //Vertical wins
            arrayOf(1, 0, 0, 1, 0, 0, 1 ,0, 0),
            arrayOf(0, 1, 0, 0, 1, 0, 0, 1, 0),
            arrayOf(0, 0, 1, 0, 0, 1, 0, 0, 1),
            //Diagonal wins
            arrayOf(1, 0, 0, 0, 1, 0, 0, 0, 1),
            arrayOf(0, 0, 1, 0, 1, 0, 1, 0, 0)
        )

        //Function to react to a box being clicked
        fun boxClicked(index:Int) {
            //If the tile has not already been changed
            if(gameGrid[index] == 0){
                //If it is player 1's turn
                if(p1Turn){
                    gameGrid[index] = 1
                    p1Grid[index] = 1
                }
                else{
                    gameGrid[index] = 2
                    p2Grid[index] = 1
                }
                //Alternate which player's turn it is
                p1Turn = !p1Turn
                //Increment total turns
                totalTurns++
                //If p1 has won
                if(checkWin(winningStates, p1Grid)){
                    //Increment score and set p1 win boolean to true
                    p1Score++
                    p1Win = true
                }
                //If p2 has won
                else if(checkWin(winningStates, p2Grid)){
                    //Increment score and set p2 win boolean to true
                    p2Score++
                    p2Win = true
                }
                //If neither player has won and the board is full
                if(totalTurns == 9) {
                    //Set draw boolean to true
                    draw = true
                }
            }
        }

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
                        "Player 1 Score: $p1Score",
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
                                            boxClicked(index);
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
            //Box to fill space at bottom
            Box(
                modifier = Modifier
                    .weight(0.3f)
            )
        }

        //If either of the players has won or the board is full
        if(p1Win || p2Win || draw){
            //Set the text for the alert dialog
            var alertText = ""
            if(p1Win){ alertText = "Player 1 Wins!" }
            else if(p2Win){ alertText = "Player 2 Wins!" }
            else{ alertText = "Draw!" }

            //Display a dialog to show the result
            Dialog(onDismissRequest = {}) {
                //Card to show the result
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(){
                        //Text to show the result
                        Text(
                            text = alertText,
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center,
                        )
                        //Row of buttons to either go back to the home page or play again
                        Row(){
                            //Button to go back to the home page
                            Button(
                                onClick = { navController.navigate("MainMenu") },
                            ) {
                                Text("Home")
                            }
                            //Button to play again
                            Button(
                                onClick = {
                                    //Reset the game
                                    p1Win = false
                                    p2Win = false
                                    draw = false
                                    gameGrid = Array(9) { 0 }
                                    p1Grid = Array(9) { 0 }
                                    p2Grid = Array(9) { 0 }
                                    totalTurns = 0
                                }
                            ){
                                Text("Play Again")
                            }
                        }
                    }
                }
            }
        }
    }
}