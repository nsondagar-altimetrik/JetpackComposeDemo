package com.nikunjsondagar.feature_users.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import com.nikunjsondagar.feature_users.NetworkUtil
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
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(NetworkUtil.SERVER_URL)
            .httpHeaders(
                httpHeaders = listOf(
                    HttpHeader(
                        "Authorization",
                        "bearer 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"
                    )
                )
            )
            .build()
    }

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