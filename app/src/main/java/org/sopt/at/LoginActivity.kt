package org.sopt.at

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import kotlinx.coroutines.launch
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.AppColors

private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

class LoginActivity : ComponentActivity() {

    private val myViewModel: MyViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idState = mutableStateOf("")

        val pwState = mutableStateOf("")
        val registeredId = mutableStateOf("")
        val registeredPw = mutableStateOf("")

        val signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val pw = result.data?.getStringExtra("pw") ?: ""

                    idState.value = id
                    pwState.value = pw
                    registeredId.value = id
                    registeredPw.value = pw

                }
            }

        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Login(
                        idState = idState,
                        pwState = pwState,
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity1::class.java)
                            signUpLauncher.launch(intent)
                        },
                        registeredId = registeredId.value,
                        registeredPw = registeredPw.value,
                        myViewModel = myViewModel
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(
    modifier: Modifier = Modifier,
    idState: MutableState<String>,
    pwState: MutableState<String>,
    onSignUpClick: () -> Unit,
    registeredId: String,
    registeredPw: String,
    myViewModel: MyViewModel
) {
    val context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.background(AppColors.secondary),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .background(AppColors.background,)
                .then(modifier),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "<",
                color = AppColors.white,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 20.dp)
            )

            Text(
                text = "TVING ID 로그인",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = AppColors.white,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp)
            )

            Column {
                OutlinedTextField(
                    value = idState.value,
                    onValueChange = { idState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .padding(top = 10.dp),
                    label = { Text("아이디", color = AppColors.secondary) },
                    singleLine = true,
                    textStyle = TextStyle(color = AppColors.white),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.gray3,
                        unfocusedContainerColor = AppColors.gray3,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )

                OutlinedTextField(
                    value = pwState.value,
                    onValueChange = { pwState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .padding(top = 10.dp),
                    label = { Text("비밀번호", color = AppColors.secondary) },
                    singleLine = true,
                    textStyle = TextStyle(color = AppColors.white),
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
                                tint = AppColors.secondary
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.gray3,
                        unfocusedContainerColor = AppColors.gray3,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
            }

            Button(
                onClick = {
                    when {
                        idState.value != registeredId || pwState.value != registeredPw -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("회원가입된 정보와 일치하지 않습니다.")
                            }
                        }
                        else -> {
                            myViewModel.setUserId(idState.value)
                            val intent = Intent(context, MainActivity::class.java).apply {
                                putExtra("id", idState.value)
                            }
                            context.startActivity(intent)
                        }
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 15.dp)
                    .width(400.dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, AppColors.primary)
            ) {
                Text("로그인하기", color = Color(0xFF7F7F7F), fontWeight = FontWeight.Bold)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("아이디찾기 ", color = AppColors.gray5, fontSize = 15.sp)
                Text(" | ", color = AppColors.gray5, fontSize = 15.sp)
                Text(" 비밀번호찾기 ", color = AppColors.gray5, fontSize = 15.sp)
                Text(" | ", color = AppColors.gray5, fontSize = 15.sp)
                Text(
                    " 회원가입 ", color = AppColors.gray5, fontSize = 15.sp,
                    modifier = Modifier.clickable {
                        onSignUpClick()
                    }
                )
            }

            Text(
                text = "      이 사이트는 Gooogle reCAPTCHA로 보호되며,\nGoogle 개인정보 처리방침과 서비스 약관이 적용됩니다.",
                style = TextStyle(color = AppColors.secondary),
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 295.dp)
                    .padding(start = 10.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ATSOPTANDROIDTheme {
    }
}