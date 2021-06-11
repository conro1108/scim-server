package com.example.scimapp.api

import com.example.scimapp.entity.ScimUser
import com.example.scimapp.impl.UsersManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController {

    var manager: UsersManager = UsersManager()

    @GetMapping("/Users")
    fun getUsers(): List<ScimUser> {
        return manager.getUsers()
    }

    @PostMapping("/Users")
    fun createUser(@RequestBody dto: ScimUserDTO): ScimUser {
        return manager.addUser(dto)
    }
}