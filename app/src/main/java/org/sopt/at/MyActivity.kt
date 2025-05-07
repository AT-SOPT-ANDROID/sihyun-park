package org.sopt.at

import android.util.Log
import android.widget.Toast
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
import kotlinx.coroutines.launch
import org.sopt.at.ui.theme.AppColors
import org.sopt.at.remote.ServicePool

@Composable
fun MyScreen(navController: NavController, viewModel: MyViewModel) {
    val userIdStr by viewModel.userId.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var nickname by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val userId by viewModel.userId.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        val tempId = userId
        if (tempId != null) {
            viewModel.getMyInfo(
                userId = tempId,
                onSuccess = { nickname = it },
                onFailure = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(context, "userId가 유효하지 않음", Toast.LENGTH_SHORT).show()
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ID: $userIdStr",
            style = TextStyle(fontSize = 20.sp, color = AppColors.white)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "닉네임: $nickname",
            style = TextStyle(fontSize = 20.sp, color = AppColors.white)
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
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, AppColors.primary)
        ) {
            Text("로그아웃", color = AppColors.gray1, fontWeight = FontWeight.Bold)
        }
    }
}
