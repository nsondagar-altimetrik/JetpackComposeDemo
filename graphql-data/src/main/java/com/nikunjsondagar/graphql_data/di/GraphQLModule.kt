package com.nikunjsondagar.graphql_data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GraphQLModule {

    @Provides
    @Singleton
    fun provideApolloClient(@Named("serverURL") serverURL: String,
                            @Named("apiToken") token: String): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(serverURL)
            .httpHeaders(
                httpHeaders = listOf(
                    HttpHeader(
                        "Authorization",
                        "bearer $token"
                    )
                )
            )
            .build()
    }

}