package com.example.hada_a3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Simon says game
class SimonSays {
    //Game where player copies a sequence of lights

    //Data class for a box
    data class LightBox( val colour: Int )

    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController) {
        //Declare variables
        var playing by rememberSaveable { mutableStateOf(false) }
        var gameOver by rememberSaveable { mutableStateOf(false) }
        var started by rememberSaveable { mutableStateOf(false) }
        var round by rememberSaveable { mutableIntStateOf(0) }
        var hiScore by rememberSaveable { mutableIntStateOf(0) }
        val defaultColors = listOf(
            Color.Red,
            Color.Blue,
            Color.Yellow,
            Color.Green
        )
        val scope = rememberCoroutineScope()
        //ArrayList for the sequence
        var sequence by rememberSaveable { mutableStateOf(ArrayList<Int>()) }
        var playerSequence by rememberSaveable { mutableStateOf(ArrayList<Int>()) }

        //State list of lightBoxes
        var boxes by rememberSaveable { mutableStateOf(defaultColors.map {LightBox(it.toArgb())})}

        //Function to light up a box
        fun lightUp(index: Int) {
            //Declare variables
            val originalColor = defaultColors[index]
            //Lighten the colour
            val lightened = originalColor.copy(
                red = (originalColor.red + 1.5f).coerceAtMost(1f),
                green = (originalColor.green + 1.5f).coerceAtMost(1f),
                blue = (originalColor.blue + 1.5f).coerceAtMost(1f)
            ).toArgb()

            //Use a coroutine to light up the box as it uses a delay
            scope.launch {
                //Set box colour to lightened color
                boxes = boxes.toMutableList().also { it[index] = LightBox(lightened) }
                //Wait for 300ms
                delay(300)
                // Set back to original color
                boxes = boxes.toMutableList().also { it[index] = LightBox(originalColor.toArgb())}
            }
        }

        //Function to show the sequence to copy
        fun showSequence() {
            //Increment the round
            round++
            //Set playing to false
            playing = false
            //Use a coroutine to light up the boxes as it uses a delay
            scope.launch {
                //For each step in the sequence
                for (index in sequence) {
                    //Light up the boxes in the sequence to copy with a delay
                    lightUp(index)
                    delay(700)
                }
                //Clear the player's sequence
                playerSequence.clear()
                //Set playing to true
                playing = true
            }
        }

        //Function to add a step to the sequence to copy
        fun addStepToSequence() {
            val newStep = (0..3).random()
            sequence = (sequence + newStep) as ArrayList<Int>
        }

        //Function to define the behaviour of each button
        fun buttonClicked(index: Int){
            //If playing is true
            if (playing) {
                //Light up the box that has been clicked
                lightUp(index)
                //Add the box to the player's sequence
                playerSequence.add(index)

                //Check if the player's sequence matches the sequence
                val index = playerSequence.lastIndex
                if (playerSequence[index] != sequence[index]) {
                    //Set playing to false if the wrong box is pressed
                    playing = false
                    //Set lost to true
                    gameOver = true
                }
                //If the sequences match
                else if (playerSequence.size == sequence.size) {
                    //Set playing to false
                    playing = false
                    //Start the next round
                    scope.launch {
                        delay(1000)
                        addStepToSequence()
                        showSequence()
                    }
                }
            }
        }

        //Column to hold the game
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //If round is greater than 0
            if(round > 0){
                //Text to show the round
                Text(text = "Round: $round")
            }
            //Add a spacer
            Spacer(modifier = Modifier.height(30.dp))

            //Row to hold the first row of boxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                //Draw each box with a spacer between
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(boxes[0].colour))
                        .clickable {
                            //Lighten the colour of the box if playing
                            buttonClicked(0)
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(boxes[1].colour))
                        .clickable {
                            //Lighten the colour of the box
                            buttonClicked(1)
                        }
                )
            }

            //Draw a spacer between the two rows
            Spacer(modifier = Modifier.height(8.dp))

            //Row to hold the second row of boxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                //Draw the other two boxes with a spacer between
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(boxes[2].colour))
                        .clickable {
                            //Lighten the colour of the box
                            buttonClicked(2)
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(boxes[3].colour))
                        .clickable {
                            buttonClicked(3)
                        }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            //If the game has not started
            if(!started){
                //Show the button
                Button(onClick = {
                    //Initialise game variables
                    sequence.clear()
                    playerSequence.clear()
                    playing = true
                    started = true
                    addStepToSequence()
                    showSequence()
                }) {
                    Text("Start Game")
                }
            }
        }
        //If the game is over
        if(gameOver){
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
                        //Update hi score
                        if(round > hiScore){ hiScore = round }
                        //Text to show the result
                        Text(
                            text = "Game Over\nGot to Round $round\nHi-Score: $hiScore",
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                        //Add a spacer between the text and buttons
                        Spacer(modifier = Modifier.height(30.dp))
                        //Row of buttons to either go back to the home page or play again
                        Row(){
                            //Button to go back to the home page
                            Button(
                                onClick = {
                                    //Set game over to false so that the dialog disappears when the button is clicked
                                    gameOver = false
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
                                //Set lost and started to false
                                onClick = {
                                    started = false
                                    gameOver = false
                                    round = 0
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