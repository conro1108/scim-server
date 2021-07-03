package com.example.scimapp.api

import com.example.scimapp.api.user.Email
import com.example.scimapp.api.user.ScimName
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

class ScimUserDTO (
    val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:User"),
    val userName: String,
    val name: ScimName?,
    val emails: List<Email>?,
    val active: Boolean?,
    val externalId: String?,
    val groups: List<UUID>?
) {
    fun findPrimaryEmail(): Email? {
        if (emails?.isEmpty() == true) { return null }
        if (emails?.size == 1) { return emails[0] }
        return emails?.single { it.primary ?: false }
    }
}

