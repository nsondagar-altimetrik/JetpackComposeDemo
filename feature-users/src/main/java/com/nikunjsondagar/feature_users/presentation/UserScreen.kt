@file:OptIn(ExperimentalMaterial3Api::class)

package com.nikunjsondagar.feature_users.presentation

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nikunjsondagar.feature_users.R
import com.nikunjsondagar.feature_users.domain.UserDetails

@Composable
fun DisplayUsersList(
    state: UserListViewModel.UserListState,
    onUserDetailsSelect: (String) -> Unit
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
                            .clickable { onUserDetailsSelect(user.profileURL) }, userDetails = user
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserItem() {
    UserItem(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
        userDetails = UserDetails(
            name = "Nikunj Sondagar",
            avatarURL = "https://avatars.githubusercontent.com/u/131298169?u=b0c5c6c8307b4678d34ce56afdb04f4bc54f25cf&v=4",
            noOfRepositoriesAvailable = "2",
            profileURL = "https://github.com/nsondagar-altimetrik"
        ).apply {
            noOfFollowers.value = 6
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(modifier: Modifier, userDetails: UserDetails) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userDetails.avatarURL)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.profile_image_accessibility_text),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(10.dp)
            ) {
                Text(
                    text = userDetails.name ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
                Spacer(modifier = Modifier.size(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(
                            id = R.string.total_repo_count_label,
                            userDetails.noOfRepositoriesAvailable
                        ),
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(
                            id = R.string.total_followers_count_label,
                            userDetails.noOfFollowers.value
                        ),
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
            Button(
                modifier = Modifier.wrapContentSize(), onClick = {
                    updateUserFollowers(userDetails = userDetails)
                }, shape = RoundedCornerShape(10.dp)
            ) {
                val buttonText =
                    if (userDetails.isFollowed.value)
                        stringResource(id = R.string.unfollow_button_text)
                    else
                        stringResource(id = R.string.follow_button_text)
                Text(text = buttonText)
            }
        }
    }
}

fun updateUserFollowers(userDetails: UserDetails) {
    userDetails.isFollowed.value = !userDetails.isFollowed.value
    if (userDetails.isFollowed.value) {
        userDetails.noOfFollowers.value = userDetails.noOfFollowers.value.plus(1)
    } else {
        userDetails.noOfFollowers.value = userDetails.noOfFollowers.value.minus(1)
    }
}