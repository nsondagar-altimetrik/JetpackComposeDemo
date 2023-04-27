package com.nikunjsondagar.feature_repos.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Repository(
    val name: String,
    val description: String,
    var language: RepositoryLanguage? = null,
    val repositoryOwner: String,
    val stargazerCount: MutableState<Int> = mutableStateOf(0),
    val repoURL: String,
    val isStarred : MutableState<Boolean> = mutableStateOf(false)
)

class RepositoryLanguage{
    var languageName: String = ""
    var languageColor : String = ""
}

