package com.example.scimapp.api.groups

import com.example.scimapp.api.ResourceNotFoundException
import com.example.scimapp.api.users.ScimUserDTO
import com.example.scimapp.persistence.ScimGroup
import com.example.scimapp.persistence.ScimUser
import com.example.scimapp.services.GroupsManager
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class GroupsController(private val manager: GroupsManager) {

    @GetMapping("/Groups")
    fun getGroups(): List<ScimGroup> {
        return manager.getGroups()
    }

    @GetMapping("/Groups/{id}")
    fun getGroup(@PathVariable id: UUID): ScimGroup {
        return manager.getGroup(id) ?: throw ResourceNotFoundException()
    }

    @PostMapping("/Groups")
    fun createGroup(@RequestBody dto: ScimGroupDTO): ScimGroup {
        return manager.addGroup(dto)
    }
}