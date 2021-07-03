package com.example.scimapp.api.user

class ScimUserDTO (
    val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:User"),
    val userName: String,
    val name: ScimName?,
    val emails: List<Email>?,
    val active: Boolean?,
    val externalId: String?,
    val groups: List<UserGroupMembership>?
) {
    fun findPrimaryEmail(): Email? {
        if (emails?.isEmpty() == true) { return null }
        if (emails?.size == 1) { return emails[0] }
        return emails?.single { it.primary ?: false }
    }
}

data class UserGroupMembership (
    val value: String,
    val ref: String,
    val display: String
)

data class ScimName (
    val first: String?,
    val middle: String?,
    val last: String?,
    val full: String?
)

data class Email (
    val value: String,
    val type: String,
    val primary: Boolean?
)

