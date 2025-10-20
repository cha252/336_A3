package com.example.hada_a3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hada_a3.ui.theme.Hada_A3Theme
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hada_A3Theme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .paddingFromBaseline(top = 25.dp)
                ){ innerPadding ->
                    UI(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//List for the names of the games
val gameList = listOf(
    "WordGuessGame",
    "TicTacToe"
)

@Composable
fun TitlePage(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
    ){
        //Display title
        Box(
            modifier = Modifier
                .border(2.dp, Color.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1.2f)
        ){
            Text(
                "Games App",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Center)
            )
        }
        //Use a lazy vertical grid to show thumbnails of each game
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(10.dp),
            modifier = modifier.fillMaxSize().weight(3.0f)
        ) {
            items(gameList.size){ index ->
                val name = gameList[index]
                //Show the thumbnail of each game
                Box(
                    modifier = Modifier
                        .border(2.dp, Color.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    Button(onClick = { navController.navigate(name) }) {
                        Text(
                            name,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier.weight(2.0f))
    }
}

@Composable
fun UI(modifier: Modifier = Modifier){
    //navController to navigate between main screen and games
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainMenu", builder = {
        //Define the main menu composable
        composable("MainMenu"){ TitlePage(modifier, navController) }
        //Define each game's game screen composable
        composable("WordGuessGame") { WordGuessGame().PlayGame(modifier, navController) }
        composable("TicTacToe") { TicTacToe().PlayGame(modifier, navController) }
    })
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    Hada_A3Theme {
        UI()
    }
}