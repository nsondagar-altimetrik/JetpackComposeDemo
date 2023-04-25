package com.nikunjsondagar.feature_users.data

import com.nikunjsondagar.GetUsersQuery
import com.nikunjsondagar.feature_users.domain.UserDetails

fun GetUsersQuery.Edge.toUserDetails(): UserDetails {
    return UserDetails(
        name = this.node?.onUser?.name ?: "",
        avatarURL = (this.node?.onUser?.avatarUrl ?: "").toString(),
        repositoryAvailable = (this.node?.onUser?.repositories?.totalCount ?: 0) > 0,
        noOfRepositoriesAvailable = (this.node?.onUser?.repositories?.totalCount.toString())
    )
}