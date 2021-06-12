package com.example.scimapp.persistence

import com.example.scimapp.api.groups.ScimGroupDTO
import com.example.scimapp.api.users.ScimUserDTO
import org.hibernate.id.SequenceGenerator.SEQUENCE
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class ScimUser(
    var userName: String,
    var name: String,
    var active: Boolean,
    var externalId: String,
    @OneToMany var memberships: List<GroupMembership>? = emptyList(),
    @Id @GeneratedValue var id: UUID? = null,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    constructor(dto: ScimUserDTO) :
            this(dto.userName, dto.name, dto.active, dto.externalId)
}

@Entity
class GroupMembership(
    var user: ScimUser,
    var group: ScimGroup,
    @Id @GeneratedValue var id: UUID? = null
)

@Entity
class ScimGroup(
    var groupName: String,
    var groupDescription: String? = null,
    var externalId: String,
    @OneToMany var memberships: List<GroupMembership>? = emptyList(),
    @Id @GeneratedValue var id: UUID? = null,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    constructor(dto: ScimGroupDTO) :
            this(dto.groupName, dto.groupDescription, dto.externalId, dto.memberships)
}
