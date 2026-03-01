package com.engineerstech.euphoticlabs.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engineerstech.euphoticlabs.ui.theme.PrimaryColor

@Composable
fun AppToggle(
    firstLabel: String,
    secondLabel: String,
    isFirstSelected: Boolean,
    onToggleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFF5F5F5), RoundedCornerShape(16.dp))
                .padding(4.dp)
        ) {
            Row {
                ToggleButtonItem(firstLabel, isFirstSelected) { onToggleChange(true) }
                ToggleButtonItem(secondLabel, !isFirstSelected) { onToggleChange(false) }
            }
        }
    }
}

@Composable
private fun ToggleButtonItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color.White else Color.Transparent,
        border = if (isSelected) BorderStroke(1.dp, PrimaryColor) else null,
        modifier = Modifier.width(150.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding( 8.dp),
            textAlign = TextAlign.Center,
            color = if (isSelected) PrimaryColor else Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}
