package com.example.scimapp.api.group

import java.util.*

data class ScimGroup (
    val id: UUID? = null,
    val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:Group"),
    val displayName: String,
    val externalId: String,
    val members: List<GroupMembership>? = emptyList(),
)

data class GroupMembership (
    val value: UUID,
    val ref: String?,
    val display: String?,
)