package com.example.scimapp.api

import java.util.*

data class GroupMembershipDTO (
    val userId: UUID,
    val display: String
)

data class ScimGroupDTO (
    val groupName: String,
    val groupDescription: String? = null,
    val externalId: String,
    val memberships: List<GroupMembershipDTO>? = emptyList()
)


