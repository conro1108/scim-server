package com.example.scimapp.api

// sad state of things, eh?
data class ScimServiceProviderConfig (
    val schemas: List<String> = listOf("urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig"),
    val patch: Supported = Supported(false),
    val bulk: Supported = Supported(false),
    val filter: Supported = Supported(false),
    val changePassword: Supported = Supported(false),
    val sort: Supported = Supported(false),
    val etag: Supported = Supported(false),
    //todo val authenticationSchemas
    )

data class Supported (
    val supported: Boolean
    )