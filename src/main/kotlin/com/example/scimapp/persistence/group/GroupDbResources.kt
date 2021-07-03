package com.example.scimapp.persistence.group

import com.example.scimapp.api.group.GroupMembership
import com.example.scimapp.api.group.ScimGroup
import com.example.scimapp.persistence.user.User
import com.example.scimapp.services.ResourceNotFoundException
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Groups")
class Group(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    var users: MutableSet<User>,

    var groupName: String,
    var externalId: String,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    // take pre-queried list of users in along with scim DTO
    constructor(dto: ScimGroup, members: MutableSet<User> = mutableSetOf()) :
            this(groupName = dto.displayName,
                externalId = dto.externalId,
                users = members)

    fun toScimGroup(): ScimGroup {
        return ScimGroup(
            id = this.id,
            displayName = this.groupName,
            externalId = this.externalId,
            members = this.users.map { GroupMembership(
                value = it.id ?: throw ResourceNotFoundException(),
                ref = "base_url/Users/" + it.id,
                display = it.userName,
            ) },
        )
    }
}
