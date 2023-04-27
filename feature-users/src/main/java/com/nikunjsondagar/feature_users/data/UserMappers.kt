package com.nikunjsondagar.feature_users.data

import com.nikunjsondagar.feature_users.domain.UserDetails
import com.nikunjsondagar.graphql_data.GetUsersQuery

fun GetUsersQuery.Edge.toUserDetails(): UserDetails {
    return UserDetails(
        name = this.node?.onUser?.name ?: "",
        avatarURL = (this.node?.onUser?.avatarUrl ?: "").toString(),
        noOfRepositoriesAvailable = (this.node?.onUser?.repositories?.totalCount.toString()),
        profileURL = (this.node?.onUser?.url.toString()),
    ).apply {
        noOfFollowers.value = this@toUserDetails?.node?.onUser?.followers?.totalCount ?: 0
    }
}