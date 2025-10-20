package com.example.hada_a3


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.hada_a3.ui.theme.Hada_A3Theme
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
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
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize()
                    .border(2.dp, Color.White, RectangleShape)
            ){
                Row(){
                    Text(
                        "Score box to display score and whose turn it is",
                        modifier = Modifier.weight(9.0f)
                    )
                    IconButton(
                        modifier = Modifier.weight(1.0f),
                        onClick = { navController.navigate("") }
                    ) { }
                }
            }

            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(80.dp)
            ){
                Column(){
                    for (i in 0..2) {
                        //Rows of buttons
                        Row(
                            Modifier
                                .align(Alignment.CenterHorizontally)
                                .weight(0.1f)
                        ) {
                            for (j in 1..3) {
                                //Define each button's onClick to update the input
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
                                    Image(
                                        painter = painterResource(id = R.drawable.blank),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )
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
    override fun PauseScreen(modifier: Modifier, navController: NavController) {
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