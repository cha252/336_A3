package com.example.hada_a3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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

//Numbers game
class Numbers {
    //4x4 Grid of numbers that randomly shuffles
    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController) {
        //Declare variables
        var level by rememberSaveable { mutableIntStateOf(0) }
        var hiScore by rememberSaveable { mutableIntStateOf(0) }
        var nextClick by rememberSaveable { mutableIntStateOf(1) }
        var gameOver by rememberSaveable { mutableStateOf(false) }
        var started by rememberSaveable { mutableStateOf(false) }
        var paused by rememberSaveable { mutableStateOf(false) }

        //Make list of 16 numbers
        var numbers by rememberSaveable { mutableStateOf((1..16).toList().shuffled()) }
        //Map of the numbers and whether the number has been clicked or not
        var clicked by rememberSaveable { mutableStateOf(Array(16) { false }) }

        //Function to set the next round
        fun nextRound(){
            //Set next click to 1
            nextClick = 1
            //Reshuffle the numbers
            numbers = numbers.shuffled()
            //Reset the clicked array
            clicked = Array(16) { false }
            //Increment level
            level++
        }

        //Function to check which box has been clicked
        fun checkClick(index: Int) {
            //Only react to a click if the game has been started
            if(started){
                //If clicked box contains number of next click
                if(numbers[index] == nextClick){
                    //If next click is 16
                    if(nextClick == 16) {
                        //Start next round
                        nextRound()
                    }else{
                        //Set clicked box to true
                        clicked[index] = true
                        //Increment nextClick
                        nextClick++
                    }
                }
                else {
                    //If the box has not already been clicked
                    if(!clicked[index]) {
                        //Set game over to true
                        gameOver = true
                    }
                }
            }
        }

        //Function to start the game
        fun startGame(){
            //Initialise variables
            level = 0
            nextClick = 1
            gameOver = false
            started = true
            //Shuffle the numbers
            numbers = numbers.shuffled()
            //Set up first round
            nextRound()
        }

        //Column to hold the game
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Add a spacer
            Spacer(modifier = Modifier.height(30.dp))

            //Row to hold the game itself
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                //Draw the grid
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    for (i in 0..3) {
                        //Rows of buttons
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            for (j in 0..3) {
                                //Draw each box of the grid and define the onclick
                                Box(
                                    modifier = Modifier
                                        .border(2.dp, Color.White, RectangleShape)
                                        .width(75.dp)
                                        .height(75.dp)
                                        .background(if(clicked[i * 4 + j]) Color.DarkGray else Color.Black)
                                        .clickable {
                                            checkClick(i * 4 + j)
                                        },
                                    contentAlignment = Alignment.Center

                                ) {
                                    var boxText = ""
                                    if((nextClick < 16-level)&&started){
                                        boxText = numbers[i * 4 + j].toString()
                                    }
                                    Text(boxText)
                                }
                            }
                        }
                    }
                }
            }

            //Add a spacer
            Spacer(Modifier.height(30.dp))

            //If the game has not been started
            if(!started){
                //Button to start the game
                Button(
                    onClick = {
                        startGame()
                    }
                ) {
                    Text("Start")
                }
            }else{
                //Otherwise add a pause button
                Button(
                    onClick = { paused = true }
                ){
                    Text("Pause")
                }
            }
        }
        //If game over or paused is true
        if(gameOver || paused){
            //Display a dialog to show the result - modified version of the minimal dialog from https://developer.android.com/develop/ui/compose/components/dialog
            Dialog(onDismissRequest = {}) {
                //Card to show the result
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        //If the game is over
                        if(gameOver){
                            //Update hi score
                            if(level > hiScore){ hiScore = level }
                            //Text to show the result
                            Text(
                                text = "Game Over\nGot to Level $level\nHi-Score: $hiScore",
                                modifier = Modifier
                                    .wrapContentSize(Alignment.Center),
                                textAlign = TextAlign.Center
                            )
                        }
                        //If game is paused
                        else{
                            Text(
                                text = "Paused",
                                modifier = Modifier
                                    .wrapContentSize(Alignment.Center),
                                textAlign = TextAlign.Center
                            )
                        }
                        //Add a spacer between the text and buttons
                        Spacer(modifier = Modifier.height(30.dp))
                        //Row of buttons to either go back to the home page or play again
                        Row(){
                            //Button to go back to the home page
                            Button(
                                onClick = {
                                    //Set booleans to false so that the dialog disappears when the button is clicked
                                    gameOver = false
                                    paused = false
                                    //Navigate back to the home page
                                    navController.navigate("MainMenu")
                                }
                            ) {
                                Text("Home")
                            }
                            //Add a spacer between the buttons
                            Spacer(modifier = Modifier.width(30.dp))
                            //Button to play again
                            Button(
                                //Set game over to false and level to 0
                                onClick = {
                                    //If game is over
                                    if(gameOver){
                                        //Reset the game
                                        startGame()
                                    }
                                    //If the home button is pressed
                                    else{
                                        //Set paused to false
                                        paused = false
                                    }
                                }
                            ){
                                //Change text based on whether game over or home button
                                if(gameOver){
                                    Text("Play Again")
                                }else{
                                    Text("Continue")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}