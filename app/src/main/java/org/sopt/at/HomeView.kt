package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavController
import org.sopt.at.ui.theme.AppColors

@Composable
fun Home(
    navController: NavController,
    viewModel: MyViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background,)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.tving_text_logo),
                contentDescription = "TVING Logo",
                modifier = Modifier.height(35.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_connect),
                    contentDescription = "Connect Icon",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 15.dp),
                    colorFilter = ColorFilter.tint(AppColors.white)
                )
                Image(
                    painter = painterResource(id = R.drawable.my_icon),
                    contentDescription = "My Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.navigate("my")
                        }
                )
            }
        }

        val homeCategories = remember { listOf("DRAMA", "VARIETY", "MOVIE", "SPORTS", "ANIMATION") }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(homeCategories) { category ->
                Text(
                    text = category,
                    color =AppColors.white,
                    fontSize = 16.sp,
                )
            }
        }

        val BannerImageList = listOf(
            R.drawable.drama1_poster_image,
            R.drawable.drama2_poster_image,
            R.drawable.drama3_poster_image,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(BannerImageList) { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Poster",
                    modifier = Modifier
                        .size(width = 400.dp, height = 500.dp)
                        .clip(RoundedCornerShape(30.dp))
                )
            }

        }


        val posterList2 = listOf(
            R.drawable.drama4_poster_image,
            R.drawable.drama5_poster_image,
            R.drawable.drama6_poster_image,
            R.drawable.drama7_poster_image,
            R.drawable.drama8_poster_image,
            R.drawable.drama9_poster_image,
        )

        Text(
            text = "오늘의 티빙 top 20",
            color = AppColors.white,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp, top = 24.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            itemsIndexed(posterList2) { index, imageRes ->
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.height(170.dp)
                ) {
                    Text(
                        text = "${index + 1}",
                        color = AppColors.white,
                        fontSize = 90.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(end = 1.dp)
                    )

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Poster",
                        modifier = Modifier
                            .size(width = 150.dp, height = 170.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
            }
        }


        val posterList3 = listOf(
            R.drawable.drama9_poster_image,
            R.drawable.drama8_poster_image,
            R.drawable.drama7_poster_image,
            R.drawable.drama6_poster_image,
            R.drawable.drama5_poster_image,
            R.drawable.drama4_poster_image,
        )

        Text(
            text = "지금 방영 중인 콘텐츠",
            color = AppColors.white,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp, top = 24.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(posterList3) { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Poster",
                    modifier = Modifier
                        .size(width = 150.dp, height = 170.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
}