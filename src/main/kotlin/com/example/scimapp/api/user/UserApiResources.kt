package com.example.scimapp.api.user

import com.example.scimapp.api.group.GroupMembership
import com.example.scimapp.persistence.user.EmailData
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

class ScimUser (
    val id: UUID? = null,
    val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:User"),
    val userName: String,
    val name: Name? = null,
    val emails: List<Email>,
    val active: Boolean? = null,
    val externalId: String? = null,
    val groups: List<GroupMembership>? = null
) {
    fun findPrimaryEmail(): Email {
        if (emails.size == 1) { return emails[0] }
        return emails.single { it.primary ?: false }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Name (
    val givenName: String? = null,
    val middleName: String? = null,
    val familyName: String? = null,
    val formatted: String? = null
)

data class Email (
    val value: String,
    val type: String,
    val primary: Boolean? = false
) {
    fun toEmailData(): EmailData {
        return EmailData(value, type)
    }
}

