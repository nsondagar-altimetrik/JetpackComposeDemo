package com.nikunjsondagar.feature_users.data

import com.nikunjsondagar.feature_users.domain.UserDetails
import com.nikunjsondagar.graphql_data.GetUsersQuery

fun GetUsersQuery.Edge.toUserDetails(): UserDetails? {
    return this?.node?.onUser?.let { user ->
        UserDetails(
            name = user.name ?: "NA",
            avatarURL = user.avatarUrl?.toString(),
            noOfRepositoriesAvailable = user.repositories?.totalCount.toString(),
            profileURL = user.url.toString()
        ).apply {
            noOfFollowers.value = user.followers?.totalCount ?:0
        }
    }?: kotlin.run { null }
}