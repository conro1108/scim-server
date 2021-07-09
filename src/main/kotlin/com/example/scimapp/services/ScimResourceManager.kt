package com.example.scimapp.services

import com.example.scimapp.api.ChunkRequest
import com.example.scimapp.api.ListResponse
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

    fun getUsers(startIndex: Int?, count: Int?): ListResponse<ScimUser> {
        startIndex?.let { startIdx -> count?.let { count ->
            // -1 because scim pag is 1-indexed
            return ListResponse(
                userRepository.findAll(ChunkRequest(count, startIdx - 1))
                    .map { it.toScimUser() }
            )
        } }
        return ListResponse(userRepository.findAll().map { it.toScimUser() })
    }

    fun addUser(dto: ScimUser): ScimUser {
        return userRepository.save(User(dto)).toScimUser()
    }

    fun getUser(id: UUID): ScimUser {
        return userRepository.findByIdOrNull(id)?.toScimUser() ?: throw ResourceNotFoundException()
    }

    fun replaceUser(id: UUID, scimUser: ScimUser): ScimUser {
        val dbUser = userRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException()
        return userRepository.save(
            User(scimUser).apply { this.id = dbUser.id }
        ).toScimUser()
    }

    fun getGroups(startIndex: Int?, count: Int?): ListResponse<ScimGroup> {
        startIndex?.let { startIdx -> count?.let { count ->
            // -1 because scim pag is 1-indexed
            return ListResponse(
                groupRepository.findAll(ChunkRequest(count, startIdx - 1))
                    .map { it.toScimGroup() }
            )
        } }
        return ListResponse(groupRepository.findAll().map { it.toScimGroup() })
    }

    fun addGroup(dto: ScimGroup): ScimGroup {
        // if members passed, fetch user entities from db to persist memberships thru JPA
        // todo this seems a lil dumb we just need to write to memberships table
        // but on the other hand, this way we know that passed ids are indeed users in our db..
        dto.members?.let { members ->
            val users = userRepository.findAllById(
                members.map { it.value }
            ).toMutableSet()
            return groupRepository.save(Group(dto, users)).toScimGroup()
        }
        return groupRepository.save(Group(dto)).toScimGroup()
    }

    fun replaceGroup(id: UUID, scimGroup: ScimGroup): ScimGroup {
        var dbGroup = groupRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException()
        return groupRepository.save(
            Group(scimGroup).apply { this.id = dbGroup.id }
        ).toScimGroup()
    }

    fun getGroup(id: UUID): ScimGroup {
        return groupRepository.findByIdOrNull(id)?.toScimGroup() ?: throw ResourceNotFoundException()
    }
}