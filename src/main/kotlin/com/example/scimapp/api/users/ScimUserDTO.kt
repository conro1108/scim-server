package com.example.scimapp.api.users

data class ScimUserDTO(
    val userName: String,
    val name: String,
    val active: Boolean,
    val externalId: String,
    val groups: List<String>
)
