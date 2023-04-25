package com.nikunjsondagar.feature_users.data

import com.apollographql.apollo3.ApolloClient
import com.nikunjsondagar.GetUsersQuery
import com.nikunjsondagar.feature_users.domain.UserClient
import com.nikunjsondagar.feature_users.domain.UserDetails

class ApolloUserClient(
    private val apolloClient: ApolloClient
) : UserClient {

    override suspend fun getUserList(searchText: String): List<UserDetails> {
        return apolloClient.query(GetUsersQuery())
            .execute()
            .data?.search?.edges?.mapNotNull {
            it?.toUserDetails()
        } ?: emptyList()
    }
}