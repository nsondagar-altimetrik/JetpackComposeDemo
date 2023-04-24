package com.nikunjsondagar.feature_repos

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
import androidx.compose.ui.unit.dp

@Composable
fun RepositoriesScreen() {
    val repoList = ArrayList<Repo>()
    for (i in 0..20) {
        repoList.add(Repo("Author $i", "Kotlin demo $i", "Kotlin desc $i"))
    }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        repoList.forEach {
            DisplayRepo(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayRepo(repo: Repo) {
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = repo.author,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = repo.repoName,
                color = Color.Blue,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = repo.repoDesc,
                color = Color.Blue,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }
    }
}