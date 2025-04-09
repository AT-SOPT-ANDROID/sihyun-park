package org.sopt.at

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity1: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signUpLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val id = result.data?.getStringExtra("id") ?: ""
                val pw = result.data?.getStringExtra("pw") ?: ""

                val intent = Intent().apply {
                    putExtra("id", id)
                    putExtra("pw", pw)
                }
                setResult(RESULT_OK, intent)
                finish() // 로그인 화면으로 돌아감
            }
        }

        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Signup1(
                        modifier = Modifier.padding(innerPadding),
                        onNextClick = { id ->
                            val intent = Intent(this, SignUpActivity2::class.java).apply {
                                putExtra("id", id)
                            }
                            signUpLauncher.launch(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Signup1(modifier: Modifier = Modifier, onNextClick: (String) -> Unit) {
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
                textStyle = TextStyle(color = Color.White),
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
                if (text.length < 6) {
                    Toast.makeText(context, "조건에 맞는 아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    onNextClick(text)
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 450.dp, bottom = 80.dp)
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

    }
}