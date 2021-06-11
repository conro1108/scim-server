package com.example.scimapp.entity

import com.example.scimapp.api.ScimUserDTO

data class ScimUser(val userName: String,
                    val name: String,
                    val active: Boolean,
                    val externalId: String,
                    val groups: List<String>,
                    val id: String
) {
    constructor(dto: ScimUserDTO, id: String) :
            this(dto.userName, dto.name, dto.active, dto.externalId, dto.groups, id)
}