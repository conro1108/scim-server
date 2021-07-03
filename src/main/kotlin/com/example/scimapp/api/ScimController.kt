package com.example.scimapp.api

import com.example.scimapp.ResourceNotFoundException
import com.example.scimapp.api.user.ScimUser
import com.example.scimapp.persistence.Group
import com.example.scimapp.persistence.User
import com.example.scimapp.services.ScimResourceManager
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ScimController(private val scimResourceManager: ScimResourceManager) {

    @GetMapping(URIPaths.USERS)
    fun getUsers(): List<User> {
        return scimResourceManager.getUsers()
    }

    @GetMapping(URIPaths.USERS_ID)
    fun getUser(@PathVariable id: UUID): User {
        return scimResourceManager.getUser(id) ?: throw ResourceNotFoundException()
    }

    @PostMapping(URIPaths.USERS)
    fun createUser(@RequestBody dto: ScimUser): ScimUser {
        return scimResourceManager.addUser(dto)
    }

    @GetMapping(URIPaths.GROUPS)
    fun getGroups(): List<Group> {
        return scimResourceManager.getGroups()
    }

    @GetMapping(URIPaths.GROUPS_ID)
    fun getGroup(@PathVariable id: UUID): Group {
        return scimResourceManager.getGroup(id) ?: throw ResourceNotFoundException()
    }

    @PostMapping(URIPaths.GROUPS)
    fun createGroup(@RequestBody dto: ScimGroupDTO): Group {
        return scimResourceManager.addGroup(dto)
    }
}
