package com.example.scimapp.persistence

import com.example.scimapp.api.ScimGroupDTO
import com.example.scimapp.api.user.ScimUserDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class ScimUser(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @JsonBackReference
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    var groups: MutableSet<ScimGroup> = mutableSetOf(),

    var userName: String,
    // only store & return full name, but arbitrary input handled in API
    var name: String,
    // only handle primary email
    var email: String?,
    var emailType: String?,

    var active: Boolean,
    var externalId: String?,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    constructor(dto: ScimUserDTO) :
            this(
                userName = dto.userName,
                // todo validate input
                //  ( full || (first && last) ) && (full == first + middle? + last)
                name = dto.name?.full ?: (dto.name?.first + (dto.name?.middle ?: "") + dto.name?.last),
                // todo validate primary exists (or 0-1 exist)
                email = dto.findPrimaryEmail()?.value,
                emailType = dto.findPrimaryEmail()?.type,
                active = dto.active ?: false,
                externalId = dto.externalId
            )
}

@Entity
class ScimGroup(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    var users: MutableSet<ScimUser>,

    var groupName: String,
    var groupDescription: String? = null,
    var externalId: String,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    // ideally we would just not even attempt to update memberships if not passed in - prefer
    // to leave membership changes explicit rather than implicit
    constructor(dto: ScimGroupDTO, members: MutableSet<ScimUser> = mutableSetOf()) :
            this(groupName = dto.groupName,
                groupDescription = dto.groupDescription,
                externalId = dto.externalId,
                users = members)
}
