package com.nikunjsondagar.feature_users.domain

data class UserDetails(
    val name: String?,
    val avatarURL: String?,
    val repositoryAvailable: Boolean,
    val noOfRepositoriesAvailable : String,
    val isFollowed : Boolean = false
)
