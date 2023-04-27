package com.nikunjsondagar.feature_users.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class UserDetails(
    val name: String?,
    val avatarURL: String?,
    var noOfFollowers: MutableState<Int> = mutableStateOf(0),
    val noOfRepositoriesAvailable: String,
    val profileURL: String,
    val isFollowed: MutableState<Boolean> = mutableStateOf(false)
)
