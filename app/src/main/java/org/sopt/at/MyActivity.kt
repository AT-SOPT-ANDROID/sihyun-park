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
import org.sopt.at.ui.theme.AppColors

@Composable
fun MyScreen(navController: NavController, viewModel: MyViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val id by viewModel.userId.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ID: $id",
            style = TextStyle(fontSize = 20.sp, color =  AppColors.white)
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
            colors = ButtonDefaults.buttonColors(containerColor =  AppColors.primary),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp,  AppColors.primary)
        ) {
            Text("로그아웃", color =  AppColors.gray1, fontWeight = FontWeight.Bold)
        }
    }
}

