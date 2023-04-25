@file:OptIn(ExperimentalMaterial3Api::class)

package com.nikunjsondagar.feature_users

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nikunjsondagar.feature_users.domain.UserDetails
import com.nikunjsondagar.feature_users.presentation.UserListViewModel

@Composable
fun DisplayUsersList(
    state: UserListViewModel.UserListState,
    onUserDetailsSelect: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.users) { user ->
                    UserItem(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()
                            .clickable { onUserDetailsSelect }, userDetails = user
                    )
                }
            }
        }
    }
}

@Composable
fun UserItem(modifier: Modifier, userDetails: UserDetails) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(userDetails.avatarURL),
                contentDescription = stringResource(id = R.string.profile_image_accessibility_text),
                modifier = Modifier.weight(0.1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(5.dp)
            ) {
                Text(
                    text = userDetails.name ?: "",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = stringResource(
                        id = R.string.repo_available_label_text,
                        userDetails.noOfRepositoriesAvailable
                    ),
                    color = Color.Blue,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(0.4f), onClick = {
                //TODO change the value of isFollowed in user details
            }) {
                val buttonText =
                    if (userDetails.isFollowed)
                        stringResource(id = R.string.unfollow_button_text)
                    else
                        stringResource(id = R.string.follow_button_text)
                Text(text = buttonText)
            }
        }
    }
}