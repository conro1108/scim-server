package com.example.scimapp.services

import com.example.scimapp.api.users.ScimUserDTO
import com.example.scimapp.persistence.ScimUser
import com.example.scimapp.persistence.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class UsersManager(private val repository: UserRepository) {

    fun getUsers(): List<ScimUser> {
        return repository.findAll().toList();
    }

    fun addUser(dto: ScimUserDTO): ScimUser {
        return repository.save(ScimUser(dto))
    }

    fun getUser(id: UUID): ScimUser? {
        return repository.findByIdOrNull(id)
    }
}