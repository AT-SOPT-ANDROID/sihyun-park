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
                    bottomBar = {
                        BottomNavBar(
                            navController = navController,
                            currentRoute = navController.currentBackStackEntry?.destination?.route
                        )
                    }
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

enum class MainTab(val route: String, val icon: Int, val title: String) {
    HOME("home", R.drawable.icon_home, "Home"),
    SHORTS("shorts", R.drawable.icon_shorts, "Shorts"),
    LIVE("live", R.drawable.icon_live, "Live"),
    SEARCH("search", R.drawable.icon_search, "Search"),
    HISTORY("history", R.drawable.icon_history, "History")
}

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = AppColors.background,
        tonalElevation = 0.dp
    ) {
        MainTab.entries.forEach { tab ->
            val selected = currentRoute == tab.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = tab.icon),
                        contentDescription = tab.title,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(18.dp)
                    )
                },
                label = { Text(tab.title, fontSize = 10.sp) },
                selected = selected,
                onClick = { navController.navigate(tab.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.white,
                    selectedTextColor = AppColors.white,
                    unselectedIconColor = AppColors.gray4,
                    unselectedTextColor = AppColors.gray4
                )
            )
        }
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
