package com.example.to_doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.to_doapp.ui.screens.homescreen.HomeScreen
import com.example.to_doapp.ui.screens.searchscreen.SearchScreen
import com.example.to_doapp.ui.screens.taskscreen.TaskScreen
import com.example.to_doapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        HomeScreen(navController)
                    }
                    composable("task/{todoId}",arguments = listOf(
                        navArgument("todoId"){
                            type = NavType.LongType
                            defaultValue = 0L
                        }
                    )){ backStackEntry ->
                        val todoId = backStackEntry.arguments?.getLong("todoId") ?: 0L
                        TaskScreen(todoId,navController)
                    }
                    composable("search"){
                        SearchScreen(navController)
                    }
                }
            }
        }
    }
}

