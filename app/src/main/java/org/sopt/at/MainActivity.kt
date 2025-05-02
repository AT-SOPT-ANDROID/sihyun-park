package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import androidx.navigation.compose.composable
import org.sopt.at.ui.theme.AppColors


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myViewModel: MyViewModel by viewModels()

        val idFromLogin = intent.getStringExtra("id") ?: "Unknown"
        myViewModel.setUserId(idFromLogin)

        setContent {
            ATSOPTANDROIDTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavBar(navController = navController) }
                ) { innerPadding ->
                    NavigationHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        myViewModel = myViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
        containerColor = AppColors.background,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.icon_home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(18.dp)
                )
            },
            label = { Text("Home",fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate("home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color(0xFF808080),
                unselectedTextColor = Color(0xFF808080)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.icon_shorts),
                    contentDescription = "Short",
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(18.dp)
                )
            },
            label = { Text("Shorts",fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate("shorts") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color(0xFF808080),
                unselectedTextColor = Color(0xFF808080)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.icon_live),
                    contentDescription = "Live",
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(18.dp)
                )
            },
            label = { Text("Live",fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate("live") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color(0xFF808080),
                unselectedTextColor = Color(0xFF808080)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.icon_search),
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(18.dp)
                )
            },
            label = { Text("Search",fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate("search") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color(0xFF808080),
                unselectedTextColor = Color(0xFF808080)
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.icon_history),
                    contentDescription = "History",
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(18.dp)
                )
            },
            label = { Text("History",fontSize = 10.sp) },
            selected = false,
            onClick = { navController.navigate("history") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color(0xFF808080),
                unselectedTextColor = Color(0xFF808080)
            )
        )
    }
}

@Composable
fun NavigationHost(
    modifier: Modifier,
    navController: NavHostController,
    myViewModel: MyViewModel
) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") {
            Home(navController = navController, viewModel = myViewModel)
        }
        composable("my") {
            MyScreen(navController = navController, viewModel = myViewModel)
        }
        composable("shorts") { Shorts() }
        composable("live") { Live() }
        composable("search") { Search() }
        composable("history") { HistoryScreen() }
    }
}