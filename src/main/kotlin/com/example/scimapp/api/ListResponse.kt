package com.example.scimapp.api

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.domain.Page

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ListResponse<T>(
    val schemas: List<String> = listOf("urn:ietf:params:scim:api:messages:2.0:ListResponse"),
    val totalResults: Int,
    val startIndex: Int? = null,
    val itemsPerPage: Int? = null,
    val resources: List<T> = emptyList()
) {
    constructor(resources: List<T>) :
            this (
                totalResults = resources.size,
                resources = resources
            )

    constructor(resources: Page<T>) :
            this (
                totalResults = resources.totalElements.toInt(),
                startIndex = resources.pageable.offset.toInt() + 1,
                itemsPerPage = resources.content.size,
                resources = resources.content
            )
}