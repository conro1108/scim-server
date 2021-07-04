package com.example.scimapp.api

import com.example.scimapp.api.group.ScimGroup
import com.example.scimapp.api.user.ScimUser
import com.example.scimapp.services.ResourceNotFoundException
import com.example.scimapp.services.ScimResourceManager
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ScimController(private val scimResourceManager: ScimResourceManager) {

    @GetMapping(URIPaths.USERS)
    fun getUsers(
        @RequestParam(required = false) startIndex: Int?,
        @RequestParam(required = false) count: Int?
        ): ListResponse<ScimUser> {
        return scimResourceManager.getUsers(startIndex, count)
    }

    @GetMapping(URIPaths.USERS_ID)
    fun getUser(@PathVariable id: UUID): ScimUser {
        return scimResourceManager.getUser(id) ?: throw ResourceNotFoundException()
    }

    @PostMapping(URIPaths.USERS)
    fun createUser(@RequestBody dto: ScimUser): ScimUser {
        return scimResourceManager.addUser(dto)
    }

    @GetMapping(URIPaths.GROUPS)
    fun getGroups(): ListResponse<ScimGroup> {
        return ListResponse(scimResourceManager.getGroups())
    }

    @GetMapping(URIPaths.GROUPS_ID)
    fun getGroup(@PathVariable id: UUID): ScimGroup {
        return scimResourceManager.getGroup(id) ?: throw ResourceNotFoundException()
    }

    @PostMapping(URIPaths.GROUPS)
    fun createGroup(@RequestBody dto: ScimGroup): ScimGroup {
        return scimResourceManager.addGroup(dto)
    }
}
