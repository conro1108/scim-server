package com.example.scimapp.api.groups

import com.example.scimapp.persistence.GroupMembership

class ScimGroupDTO (
    var groupName: String,
    var groupDescription: String? = null,
    var externalId: String,
    var memberships: List<GroupMembership>? = emptyList()
)