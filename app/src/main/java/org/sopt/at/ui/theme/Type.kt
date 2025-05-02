package org.sopt.at.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.white
    ),
    labelSmall = TextStyle(
        fontSize = 10.sp,
        color = AppColors.gray1
    ),
    labelMedium = TextStyle(
        fontSize = 15.sp,
        color = AppColors.gray1
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.white
    ),
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.white
    ),
    displaySmall = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.gray2
    )
)