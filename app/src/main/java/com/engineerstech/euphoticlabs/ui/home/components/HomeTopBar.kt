package com.engineerstech.euphoticlabs.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeTopBar(
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Look up dishes", fontSize = 14.sp, color = Color.Gray) },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            leadingIcon = { 
                Icon(
                    Icons.Outlined.Search, 
                    contentDescription = null, 
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                ) 
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray
            ),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        TopBarActionIcons()
        
        Spacer(modifier = Modifier.width(12.dp))
        
        SystemActionIcons()
    }
}

@Composable
private fun TopBarActionIcons() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TopBarIconButton(Icons.Default.Restaurant)
        TopBarIconButton(Icons.Default.Kitchen)
        TopBarIconButton(Icons.Outlined.Notifications)
        TopBarIconButton(Icons.Default.CheckCircle)
    }
}

@Composable
private fun SystemActionIcons() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        SystemIconBox(Icons.Default.Wifi)
        SystemIconBox(Icons.Default.PowerSettingsNew)
        SystemIconBox(text = "P")
    }
}

@Composable
private fun TopBarIconButton(icon: ImageVector) {
    IconButton(onClick = { }, modifier = Modifier.size(36.dp)) {
        Icon(
            icon, 
            contentDescription = null, 
            tint = Color(0xFF424242), 
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
private fun SystemIconBox(icon: ImageVector? = null, text: String? = null) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(Color(0xFFF5F5F5), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.DarkGray)
        } else if (text != null) {
            Text(text, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}
