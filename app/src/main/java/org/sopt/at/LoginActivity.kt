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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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

        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val id = result.data?.getStringExtra("id") ?: ""
                    val pw = result.data?.getStringExtra("pw") ?: ""

                    idState.value = id
                    pwState.value = pw
                }
            }

        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Login(
                        id = idState.value,
                        pw = pwState.value,
                        onIdChange = { idState.value = it },
                        onPwChange = { pwState.value = it },
                        onLoginClick = {
                            myViewModel.signIn(
                                loginId = idState.value,
                                password = pwState.value,
                                onSuccess = { userId ->
                                    myViewModel.setUserId(userId)
                                    val intent = Intent(this, MainActivity::class.java).apply {
                                        putExtra("userId", userId)
                                    }
                                    startActivity(intent)
                                },
                                onFailure = { message ->
                                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                                }
                            )
                        },
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity1::class.java)
                            signUpLauncher.launch(intent)
                        }
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
    id: String,
    pw: String,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    val context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val myViewModel: MyViewModel = viewModel()

    Scaffold(
        modifier = modifier.background(AppColors.secondary),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .background(AppColors.background)
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
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp)
            )

            Column {
                OutlinedTextField(
                    value = id,
                    onValueChange = onIdChange,
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
                    value = pw,
                    onValueChange = onPwChange,
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
                    myViewModel.signIn(
                        loginId = id,
                        password = pw,
                        onSuccess = { userId ->
                            myViewModel.setUserId(userId)
                            Toast.makeText(context, "로그인 성공! userId: $userId", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, MainActivity::class.java).apply {
                                putExtra("userId", userId)
                            }
                            context.startActivity(intent)
                        },
                        onFailure = { message ->
                            Toast.makeText(context, "로그인 실패: $message", Toast.LENGTH_SHORT).show()
                        }
                    )
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
                Text("로그인하기", color = AppColors.gray5, fontWeight = FontWeight.Bold)
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
