package com.nikunjsondagar.composedemo.di

import com.nikunjsondagar.composedemo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object GraphQLConfigurationModule {

    @Provides
    @Named("serverURL")
    fun provideServerURL(): String {
        return BuildConfig.SERVER_URL
    }

    @Provides
    @Named("apiToken")
    fun provideApiToken() : String {
        return BuildConfig.API_TOKEN
    }

}