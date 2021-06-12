package com.example.scimapp.services

import com.example.scimapp.api.groups.ScimGroupDTO
import com.example.scimapp.persistence.GroupRepository
import com.example.scimapp.persistence.ScimGroup
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class GroupsManager (val repository: GroupRepository) {

    fun getGroups(): List<ScimGroup> {
        return repository.findAll().toList();
    }

    fun addGroup(dto: ScimGroupDTO): ScimGroup {
        return repository.save(ScimGroup(dto))
    }

    fun getGroup(id: UUID): ScimGroup? {
        return repository.findByIdOrNull(id)
    }
}