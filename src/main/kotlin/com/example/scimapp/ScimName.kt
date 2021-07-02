package com.example.scimapp

class ScimName private constructor(builder: Builder){

    val first: String? = builder.first
    val middle: String? = builder.middle
    val last: String? = builder.last
    val full: String? = builder.full

    class Builder {
        var first: String? = null
        var middle: String? = null
        var last: String? = null
        var full: String? = null

        fun first(first: String) { this.first = first }
        fun middle(middle: String) { this.middle = middle }
        fun last(last: String) { this.last = middle }
        fun full(full: String) { this.full = full }

        fun build() = ScimName(this)
    }
}