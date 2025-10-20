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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

class WordGuessGame {
    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController) {
        //Initialise variables
        var allGuesses by rememberSaveable { mutableStateOf(Array(6) { "" }) }
        var currentGuess by rememberSaveable { mutableStateOf("") }
        var totalGuesses by rememberSaveable { mutableIntStateOf(0) }
        var win by rememberSaveable { mutableStateOf(false) }
        var loss by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = modifier
        ){
            //Row to hold the game itself
            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(80.dp)
            ){
                //Draw the grid
                Column(){
                    for (i in 0..5) {
                        //Rows of buttons
                        Row(
                            Modifier
                                .align(Alignment.CenterHorizontally)
                                .weight(0.1f)
                        ) {
                            for (j in 0..6) {
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
                                        }
                                ) {
                                    Color.Transparent
                                }
                            }
                        }
                    }
                }
            }
            //Box to fill space at bottom
            Box(
                modifier = Modifier
                    .weight(0.05f)
            )
        }

        if(win || loss) {
            //Set the text for the alert dialog
            var alertText = ""
            if (win) {
                alertText = "You win!"
            } else {
                alertText = "You Lose!"
            }

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
                    Column() {
                        //Text to show the result
                        Text(
                            text = alertText,
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center,
                        )
                        //Row of buttons to either go back to the home page or play again
                        Row() {
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
                                    win = false
                                    loss = false
                                    allGuesses = Array(6) { "" }
                                    currentGuess = ""
                                    totalGuesses = 0
                                }
                            ) {
                                Text("Play Again")
                            }
                        }
                    }
                }
            }
        }
    }
}