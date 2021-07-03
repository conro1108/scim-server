package com.example.scimapp.api.user

data class ScimName (
    val first: String?,
    val middle: String?,
    val last: String?,
    val full: String?
)

data class Email (
    val value: String,
    val type: String,
    val primary: Boolean?
)

