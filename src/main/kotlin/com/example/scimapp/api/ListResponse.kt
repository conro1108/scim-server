package com.example.scimapp.api

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ListResponse<T>(
    val schemas: List<String> = listOf("urn:ietf:params:scim:api:messages:2.0:ListResponse"),
    val totalResults: Int,
    val startIndex: Int? = null,
    val itemsPerPage: Int? = null,
    val Resources: List<T> = emptyList()
) {
    constructor(resources: List<T>) :
            this (
                totalResults = resources.size,
                Resources = resources
            )
}