package com.nikunjsondagar.feature_users.domain

class GetUserListUseCase(private val apolloUserClient: UserClient) {

    suspend fun execute(searchText: String): List<UserDetails> {
        return apolloUserClient.getUserList(searchText).sortedBy { it.name }
    }

}