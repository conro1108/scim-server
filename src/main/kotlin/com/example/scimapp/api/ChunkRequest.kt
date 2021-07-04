package com.example.scimapp.api

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

// todo validate
data class ChunkRequest(
    val limit: Int,
    val offset: Int
) : Pageable {
    override fun getPageNumber(): Int {
        return 0
    }

    override fun getPageSize(): Int {
        return limit
    }

    override fun getOffset(): Long {
        return offset.toLong()
    }

    override fun getSort(): Sort {
        return Sort.by("id")
    }

    override fun next(): Pageable {
        return ChunkRequest(limit, offset + limit)
    }

    override fun previousOrFirst(): Pageable {
        return if (hasPrevious()) previous() else first()
    }

    private fun previous(): Pageable {
        return if (hasPrevious()) ChunkRequest(limit, offset - limit) else this
    }

    override fun first(): Pageable {
        return ChunkRequest(limit, 0)
    }

    override fun hasPrevious(): Boolean {
        return offset > limit
    }

}
