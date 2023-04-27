package com.nikunjsondagar.feature_repos.domain

class RepositoryListUseCase(
    private val repositoryClient: RepositoryClient
) {
    suspend operator fun invoke(searchText: String) =
        repositoryClient.getRepositoriesList(searchText)
}