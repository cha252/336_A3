package com.example.hada_a3

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

interface Game {
    @Composable
    fun StartScreen(modifier: Modifier, navController: NavController)

    @Composable
    fun PlayGame(modifier: Modifier, navController: NavController)

    @Composable
    fun GameOver(modifier: Modifier, navController: NavController)
}