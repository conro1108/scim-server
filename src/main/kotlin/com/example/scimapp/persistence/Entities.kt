package com.example.scimapp.persistence

import com.example.scimapp.api.ScimUserDTO
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class ScimUser(
    var userName: String,
    var name: String,
    var active: Boolean,
    var externalId: String,
    @Id @GeneratedValue var id: Long? = null,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    constructor(dto: ScimUserDTO) :
            this(dto.userName, dto.name, dto.active, dto.externalId)
}

@Entity
class GroupMembership(
    @ManyToOne var user: ScimUser,
    @ManyToOne var group: ScimGroup,
    @Id @GeneratedValue var id: Long? = null
)

@Entity
class ScimGroup(
    var groupName: String,
    var groupDescription: String? = null,
    var externalId: String,
    @Id @GeneratedValue var id: Long? = null,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
)
