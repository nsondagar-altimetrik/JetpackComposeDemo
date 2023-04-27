package com.nikunjsondagar.feature_repos.domain

interface RepositoryClient {
    suspend fun getRepositoriesList(searchText: String) : List<Repository>
}