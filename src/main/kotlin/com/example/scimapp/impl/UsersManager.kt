package com.example.scimapp.impl

import com.example.scimapp.api.ScimUserDTO
import com.example.scimapp.entity.ScimUser

class UsersManager {

    var userStore: MutableList<ScimUser> = mutableListOf()
    var newId: Int = 0;

    fun getUsers(): List<ScimUser> {
        return userStore;
    }

    fun addUser(dto: ScimUserDTO): ScimUser {
        val user = ScimUser(dto, generateId())
        userStore.add(user)
        return user
    }

    fun generateId(): String {
        return newId++.toString()
    }
}