package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.AppColors

@Composable
fun HistoryScreen(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .then(modifier)
    ) {
        Text(
            text = "History",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp, top = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    HistoryScreen()
}