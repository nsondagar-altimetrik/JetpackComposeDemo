package com.nikunjsondagar.feature_repos.data

import com.nikunjsondagar.feature_repos.domain.Repository
import com.nikunjsondagar.feature_repos.domain.RepositoryLanguage
import com.nikunjsondagar.graphql_data.GetRepositoriesQuery

fun GetRepositoriesQuery.Edge.toRepository() : Repository {
    val repo = this?.node?.onRepository
    return Repository(
        name = repo?.name ?: "",
        description = repo?.description?:"",
        repositoryOwner = repo?.owner?.login ?: "",
        repoURL = repo?.url as String
    ).apply {
        repo?.primaryLanguage?.let {
            language = RepositoryLanguage().apply {
                languageName = it.name
                languageColor = it.color.toString()
            }
        }?: kotlin.run {
            language = RepositoryLanguage().apply {
                languageName = "NA"
                languageColor = "#000000"
            }
        }
        stargazerCount.value = repo?.stargazerCount?:0
    }
}