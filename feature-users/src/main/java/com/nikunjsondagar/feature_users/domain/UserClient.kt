package com.nikunjsondagar.feature_users.domain

interface UserClient {
    suspend fun getUserList(searchText: String) : List<UserDetails>
}