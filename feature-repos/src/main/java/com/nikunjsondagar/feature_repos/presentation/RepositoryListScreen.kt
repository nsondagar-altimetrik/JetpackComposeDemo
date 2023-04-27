package com.nikunjsondagar.feature_repos.presentation

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikunjsondagar.feature_repos.R
import com.nikunjsondagar.feature_repos.domain.Repository
import com.nikunjsondagar.feature_repos.domain.RepositoryLanguage

@Composable
fun RepositoryListScreen(
    state: RepositoryViewModel.GetRepositoryListState,
    onRepositoryClick: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.repositoryList) { repository ->
                    AddRepositoryListItemColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { onRepositoryClick(repository.repoURL) },
                        repository = repository
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRepositoryListItem() {
    AddRepositoryListItemColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        repository = Repository(
            name = "Location-samples",
            description = "Multiple samples showing the best practices in location APIs on Android.",
            language = RepositoryLanguage().apply {
                languageName = "Kotlin"
                languageColor = "#A97BFF"
            },
            repositoryOwner = "android",
            repoURL = "https://github.com/android/location-samples"
        ).apply {
            stargazerCount.value = 2648
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRepositoryListItemColumn(modifier: Modifier, repository: Repository) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = repository.name, modifier = Modifier.align(Alignment.TopStart), textAlign = TextAlign.Start,
                    maxLines = 1
                )
                Row(modifier = Modifier.align(Alignment.TopEnd), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            updateRepositoryStarCount(repository)
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (repository.isStarred.value)
                                    R.drawable.star_black
                                else
                                    R.drawable.star_border_black
                            ),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = repository.stargazerCount.value.toString(),
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }
            Text(
                text = repository.description,
                textAlign = TextAlign.Start,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.align(Alignment.TopStart), verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            updateRepositoryStarCount(repository)
                        },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.created_by_icon_accessibility_text)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = repository.repositoryOwner,
                        textAlign = TextAlign.Start,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
                Row(
                    modifier = Modifier.align(Alignment.TopEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(
                                Color(
                                    parseColor(repository?.language?.languageColor)
                                )
                            )
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = repository?.language?.languageName.toString(),
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            }
        }
    }
}

fun updateRepositoryStarCount(repository: Repository) {
    repository.isStarred.value = !repository.isStarred.value
    if (repository.isStarred.value) {
        repository.stargazerCount.value += 1
    } else {
        repository.stargazerCount.value -= 1
    }
}