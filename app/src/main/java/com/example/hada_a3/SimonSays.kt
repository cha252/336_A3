package com.example.hada_a3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonSays {
    //Game where player copies a sequence of lights


    //Data class for a box
    data class lightBox( val colour: Int )

    //Enum of colours
    enum class Colours{
        RED,
        BLUE,
        YELLOW,
        GREEN
    }

    //Empty arraylist for the sequence to copy
    var sequence = ArrayList<Colours>()

    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController) {
        //Declare variables
        var playing by rememberSaveable { mutableStateOf(true) }
        var someText by rememberSaveable { mutableStateOf("true") }
        val defaultColors = listOf(
            Color.Red,
            Color.Blue,
            Color.Yellow,
            Color.Green
        )
        val scope = rememberCoroutineScope()

        // State list of lightBoxes
        var boxes by rememberSaveable { mutableStateOf(defaultColors.map {lightBox(it.toArgb())})}

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

            //Launch coroutine
            scope.launch {
                //Set box colour to lightened color
                boxes = boxes.toMutableList().also { it[index] = lightBox(lightened) }

                //Wait for 500ms
                delay(500)

                // Set back to original color
                boxes = boxes.toMutableList().also { it[index] = lightBox(originalColor.toArgb())}
            }
        }

        //Function to show the sequence to copy
        fun showSequence(){

        }

        //Column to hold the game
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
                            //Lighten the colour of the box
                            lightUp(0)
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(boxes[1].colour))
                        .clickable {
                            //Lighten the colour of the box
                            lightUp(1)
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
                            lightUp(2)
                        }                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(boxes[3].colour))
                        .clickable {
                            lightUp(3)
                        }
                )
            }
            Text(
                modifier = Modifier.padding(10.dp),
                text = someText
            )
        }
    }
}