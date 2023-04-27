package com.nikunjsondagar.feature_repos.data

import com.apollographql.apollo3.ApolloClient
import com.nikunjsondagar.feature_repos.domain.Repository
import com.nikunjsondagar.feature_repos.domain.RepositoryClient
import com.nikunjsondagar.graphql_data.GetRepositoriesQuery

class ApolloRepositoryClient(
    private val apolloClient: ApolloClient
) : RepositoryClient {
    override suspend fun getRepositoriesList(searchText: String): List<Repository> {
        return apolloClient.query(GetRepositoriesQuery())
            .execute().data?.search?.edges?.mapNotNull {
            it?.toRepository()
        } ?: emptyList()
    }
}
