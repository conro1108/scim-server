package com.example.scimapp.persistence

import com.example.scimapp.api.ScimGroupDTO
import com.example.scimapp.persistence.user.User
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
    var groupDescription: String? = null,
    var externalId: String,
    var lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    // ideally we would just not even attempt to update memberships if not passed in - prefer
    // to leave membership changes explicit rather than implicit
    constructor(dto: ScimGroupDTO, members: MutableSet<User> = mutableSetOf()) :
            this(groupName = dto.groupName,
                groupDescription = dto.groupDescription,
                externalId = dto.externalId,
                users = members)
}

