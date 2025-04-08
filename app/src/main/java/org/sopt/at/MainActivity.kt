package org.sopt.at

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Signup1() {
    var text by remember { mutableStateOf("") }
    var context = LocalContext.current

    Column(
        modifier = Modifier.background(color=Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "아이디를 입력해주세요",
            style = TextStyle(fontSize = 20.sp,color = Color(0xFFD8D8D8)),
            modifier = Modifier
                .padding(top = 20.dp)
        )

        Column() {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(top=10.dp),
                label = { Text("아이디",color = Color(0xFF505050))},
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262626),
                    unfocusedContainerColor = Color(0xFF262626),
                    focusedIndicatorColor = Color(0xFF9E9E9E),
                    unfocusedIndicatorColor = Color(0xFF9E9E9E),
                )
            )

            Text(
                text = "영문 소문자 또는 영문 소문자,숫자 조합 6~12 자리",
                style = TextStyle(color = Color(0xFF505050)),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(start=10.dp)

            )

        }

        Button(
            onClick = {
                if (text.length > 5) {
                    Toast.makeText(context, "로그인완료", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 600.dp)
                .width(400.dp)
                .border(1.dp, Color(0xFF9E9E9E), shape = RoundedCornerShape(5.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("다음", color = Color(0xFF505050))
        }
    }
}

@Composable
fun Signup2() {
    var text by remember { mutableStateOf("") }
    var context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(color=Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "비밀번호를 입력해주세요",
            style = TextStyle(fontSize = 20.sp,color = Color(0xFFD8D8D8)),
            modifier = Modifier
                .padding(top = 20.dp)
        )

        Column() {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(top=10.dp),
                label = { Text("비밀번호",color = Color(0xFF505050))},
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(imageVector = image, contentDescription = "비밀번호 보기/숨기기", tint = Color(0xFF505050))
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
                    .padding(start=10.dp)

            )

        }

        Button(
            onClick = {
                if (text.length > 5) {
                    Toast.makeText(context, "로그인완료", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 600.dp)
                .width(400.dp)
                .border(1.dp, Color(0xFF9E9E9E), shape = RoundedCornerShape(5.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("다음", color = Color(0xFF505050))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ATSOPTANDROIDTheme {
        Signup2()
    }
}