package com.example.scimapp.services

import com.example.scimapp.api.group.ScimGroup
import com.example.scimapp.api.user.ScimUser
import com.example.scimapp.persistence.GroupRepository
import com.example.scimapp.persistence.UserRepository
import com.example.scimapp.persistence.group.Group
import com.example.scimapp.persistence.user.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class ScimResourceManager(private val userRepository: UserRepository, val groupRepository: GroupRepository) {

    fun getUsers(): List<ScimUser> {
        return userRepository.findAll().map { it.toScimUser() }
    }

    fun addUser(dto: ScimUser): ScimUser {
        return userRepository.save(User(dto)).toScimUser()
    }

    fun getUser(id: UUID): ScimUser? {
        return userRepository.findByIdOrNull(id)?.toScimUser()
    }

    fun getGroups(): List<ScimGroup> {
        return groupRepository.findAll().map { it.toScimGroup() }
    }

    fun addGroup(dto: ScimGroup): ScimGroup {
        // if members passed, fetch user entities from db to persist memberships thru JPA
        // todo this is aggressively stupid we just need to write to memberships table
        // but on the other hand, this way we know that passed ids are indeed users in our db..
        dto.members?.let {
            val users: MutableSet<User> = userRepository.findAllById(
                it.map { member -> member.value }
            ).toMutableSet()
            return groupRepository.save(Group(dto, users)).toScimGroup()
        }
        return groupRepository.save(Group(dto)).toScimGroup()
    }

    fun getGroup(id: UUID): ScimGroup? {
        return groupRepository.findByIdOrNull(id)?.toScimGroup()
    }
}