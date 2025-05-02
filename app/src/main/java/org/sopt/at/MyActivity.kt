package org.sopt.at

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MyScreen(navController: NavController, viewModel: MyViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val id by viewModel.userId.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ID: $id",
            style = TextStyle(fontSize = 20.sp, color = Color.White)
        )

        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 400.dp, bottom = 300.dp)
                .width(400.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF404040)),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, Color(0xFF404040))
        ) {
            Text("로그아웃", color = Color(0xFF7F7F7F), fontWeight = FontWeight.Bold)
        }
    }
}

