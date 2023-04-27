package com.nikunjsondagar.feature_repos.di

import com.apollographql.apollo3.ApolloClient
import com.nikunjsondagar.feature_repos.data.ApolloRepositoryClient
import com.nikunjsondagar.feature_repos.domain.RepositoryClient
import com.nikunjsondagar.feature_repos.domain.RepositoryListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideApolloRepositoryClient(apolloClient: ApolloClient): RepositoryClient {
        return ApolloRepositoryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideRepositoryListUseCase(repositoryClient: RepositoryClient): RepositoryListUseCase {
        return RepositoryListUseCase(repositoryClient)
    }

}