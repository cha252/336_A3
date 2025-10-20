package com.example.hada_a3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class SimonSays {
    //Game where player copies a sequence of lights

    //Enum of colours
    enum class Colours{
        RED,
        BLUE,
        YELLOW,
        GREEN
    }

    //Empty arraylist for the sequence that needs to be pressed



    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController) {
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
                        .background(Color.Red)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Green)
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
                        .background(Color.Blue)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Yellow)
                )
            }
        }

    }
}