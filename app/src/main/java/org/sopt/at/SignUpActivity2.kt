package org.sopt.at

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.AppColors

class SignUpActivity2 : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val id = intent.getStringExtra("id") ?: ""

        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize().background(color = AppColors.background,)) {
                    Signup2(id = id)
                }
            }
        }
    }
}

@Composable
fun Signup2(id: String) {
    var text by remember { mutableStateOf("") }
    var context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(color = AppColors.background,),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "비밀번호를 입력해주세요",
            style = TextStyle(fontSize = 20.sp, color = Color(0xFFD8D8D8)),
            modifier = Modifier
                .padding(top = 70.dp)
        )

        Column() {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(top = 10.dp),
                label = { Text("비밀번호", color = Color(0xFF505050)) },
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            imageVector = image,
                            contentDescription = "비밀번호 보기/숨기기",
                            tint = Color(0xFF505050)
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262626),
                    unfocusedContainerColor = Color(0xFF262626),
                    focusedIndicatorColor = Color(0xFF9E9E9E),
                    unfocusedIndicatorColor = Color(0xFF9E9E9E),
                )
            )

            Text(
                text = "영문,숫자,특수문자(~!@#$%^&*) 조합 8~15자리",
                style = TextStyle(color = Color(0xFF505050)),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(start = 10.dp)

            )

        }

        val passwordRegex =
            Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}$")
        Button(
            onClick = {
                if (!password.matches(passwordRegex)) {
                    Toast.makeText(context, "조건에 맞는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent = Intent().apply {
                        putExtra("id", id)
                        putExtra("pw", password)
                    }

                    (context as Activity).setResult(Activity.RESULT_OK, intent)
                    (context as Activity).finish()
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 450.dp, bottom = 80.dp)
                .width(400.dp)
                .border(1.dp, Color(0xFF9E9E9E), shape = RoundedCornerShape(5.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.background,),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("다음", color = Color(0xFF505050))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ATSOPTANDROIDTheme {
    }
}