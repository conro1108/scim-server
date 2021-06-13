package com.example.scimapp.services

import com.example.scimapp.api.ScimGroupDTO
import com.example.scimapp.api.ScimUserDTO
import com.example.scimapp.persistence.GroupRepository
import com.example.scimapp.persistence.ScimGroup
import com.example.scimapp.persistence.ScimUser
import com.example.scimapp.persistence.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class ScimResourceManager(private val userRepository: UserRepository, val groupRepository: GroupRepository) {

    fun getUsers(): List<ScimUser> {
        return userRepository.findAll().toList();
    }

    fun addUser(dto: ScimUserDTO): ScimUser {
        return userRepository.save(ScimUser(dto))
    }

    fun getUser(id: UUID): ScimUser? {
        return userRepository.findByIdOrNull(id)
    }

    fun getGroups(): List<ScimGroup> {
        return groupRepository.findAll().toList()
    }

    fun addGroup(dto: ScimGroupDTO): ScimGroup {
        // if members passed, fetch user entities from db to persist memberships thru JPA
        // todo this is aggressively stupid we just need to write to memberships table
        // todo factoring feels wonky, more idiomatic pattern?
        dto.memberships?.let {
            val users: MutableSet<ScimUser> = userRepository.findAllById(
                it.stream()
                    .map { mem -> mem.userId }
                    .collect(Collectors.toList())
            ).toMutableSet()
            return groupRepository.save(ScimGroup(dto, users))
        }
        return groupRepository.save(ScimGroup(dto))
    }

    fun getGroup(id: UUID): ScimGroup? {
        return groupRepository.findByIdOrNull(id)
    }
}