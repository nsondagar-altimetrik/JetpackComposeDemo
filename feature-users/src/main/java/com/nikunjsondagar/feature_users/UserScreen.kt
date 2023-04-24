package com.nikunjsondagar.feature_users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen() {
    val userList = ArrayList<User>()
    for (i in 0..20) {
        userList.add(User("User $i", "User description $i"))
    }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        userList.forEach {
            DisplayUser(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUser(user: User) {
    Card(modifier =  Modifier.padding(all = 10.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = user.title,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = user.description,
                color = Color.Blue,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
        }
    }
}

@Composable
@Preview
fun UserScreenPreview() {
    UserScreen()
}