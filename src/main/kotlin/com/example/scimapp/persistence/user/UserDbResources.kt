package com.example.scimapp.persistence.user

import com.example.scimapp.ResourceNotFoundException
import com.example.scimapp.api.user.Email
import com.example.scimapp.api.user.Name
import com.example.scimapp.api.user.ScimUser
import com.example.scimapp.api.user.UserGroupMembership
import com.example.scimapp.persistence.Group
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.streams.toList

@Entity
class User(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    var groups: MutableSet<Group> = mutableSetOf(),

    var userName: String,
    // only store & return full name, but arbitrary input handled in API
    var name: String,
    // only handle primary email
    @Embedded
    var email: EmailData,

    var active: Boolean,
    var externalId: String?,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    constructor(dto: ScimUser) :
            this(
                userName = dto.userName,
                // todo validate input
                //  ( full || (first && last) ) && (full == first + middle? + last)
                name = dto.name?.formatted ?:
                (dto.name?.givenName + (dto.name?.middleName ?: "") + dto.name?.familyName),
                // todo validate primary exists (or 0-1 exist)
                email = dto.findPrimaryEmail().toEmailData(),
                active = dto.active ?: false,
                externalId = dto.externalId
            )

    fun toScimUser(): ScimUser {
        return ScimUser(
            userName = this.userName,
            name = Name(formatted = this.name),
            emails = listOf(this.email.toEmail()),
            active = this.active,
            externalId = this.externalId,
            groups = this.groups.parallelStream()
                .map { UserGroupMembership(
                    value = it.id ?: throw ResourceNotFoundException(),
                    ref = "base_url/Groups/" + it.id,
                    display = it.groupName
                ) }.toList(),

            )
    }
}

@Embeddable
data class EmailData (
    val emailValue: String,
    val emailType: String,
) {
    fun toEmail(): Email {
        return Email(emailValue, emailType, true)
    }
}
