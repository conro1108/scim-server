package com.example.scimapp.api

data class GroupMembershipDTO (
    val userId: String,
    val display: String
)

data class ScimGroupDTO (
    val groupName: String,
    val groupDescription: String? = null,
    val externalId: String,
    val memberships: List<GroupMembershipDTO>? = emptyList()
)

data class ScimUserDTO (
    val userName: String,
    val name: String,
    val active: Boolean,
    val externalId: String,
    val groups: List<String>
)

