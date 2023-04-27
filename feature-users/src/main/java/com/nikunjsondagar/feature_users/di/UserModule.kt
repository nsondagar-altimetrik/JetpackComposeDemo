package com.nikunjsondagar.feature_users.di

import com.apollographql.apollo3.ApolloClient
import com.nikunjsondagar.feature_users.data.ApolloUserClient
import com.nikunjsondagar.feature_users.domain.GetUserListUseCase
import com.nikunjsondagar.feature_users.domain.UserClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserClient(apolloClient: ApolloClient): UserClient {
        return ApolloUserClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetUserListUseCase(userClient: UserClient): GetUserListUseCase {
        return GetUserListUseCase(userClient)
    }

}