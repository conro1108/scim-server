package com.example.scimapp.persistence

import com.example.scimapp.api.ScimGroupDTO
import com.example.scimapp.api.ScimUserDTO
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
    var groups: MutableSet<ScimGroup>,

    var userName: String,
    var name: String,
    var active: Boolean,
    var externalId: String,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    // ideally we would just not even attempt to update memberships if not passed in
    constructor(dto: ScimUserDTO, members: MutableSet<ScimGroup> = mutableSetOf()) :
            this(userName = dto.userName,
                name = dto.name,
                active = dto.active,
                externalId = dto.externalId,
                groups = members)
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
    // ideally we would just not even attempt to update memberships if not passed in
    constructor(dto: ScimGroupDTO, members: MutableSet<ScimUser> = mutableSetOf()) :
            this(groupName = dto.groupName,
                groupDescription = dto.groupDescription,
                externalId = dto.externalId,
                users = members)
}
