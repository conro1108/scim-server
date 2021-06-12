package com.example.scimapp.api

import com.example.scimapp.persistence.ScimUser
import com.example.scimapp.services.UsersManager
import org.springframework.web.bind.annotation.*

@RestController
class UsersController(private val manager: UsersManager) {

    @GetMapping("/Users")
    fun getUsers(): List<ScimUser> {
        return manager.getUsers()
    }

    @GetMapping("/Users/{id}")
    fun getUser(@PathVariable id: Long): ScimUser {
        return manager.getUser(id) ?: throw ResourceNotFoundException()
    }

    @PostMapping("/Users")
    fun createUser(@RequestBody dto: ScimUserDTO): ScimUser {
        return manager.addUser(dto)
    }
}
