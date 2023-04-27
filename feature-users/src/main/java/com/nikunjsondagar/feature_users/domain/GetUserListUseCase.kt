package com.nikunjsondagar.feature_users.domain

class GetUserListUseCase(private val apolloUserClient: UserClient) {
    suspend operator fun invoke(searchText: String) = apolloUserClient.getUserList(searchText).sortedBy { it.name }

}