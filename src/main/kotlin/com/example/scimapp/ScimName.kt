package com.example.scimapp

class ScimName private constructor(
    val first: String?,
    val middle: String?,
    val last: String?,
    val full: String?
){
    data class Builder (
        var first: String? = null,
        var middle: String? = null,
        var last: String? = null,
        var full: String? = null) {

        fun first(first: String) = apply { this.first = first }
        fun middle(middle: String) = apply { this.middle = middle }
        fun last(last: String) = apply { this.last = middle }
        fun full(full: String) = apply { this.full = full }

        fun build() = ScimName(first, middle, last, full)
    }
}