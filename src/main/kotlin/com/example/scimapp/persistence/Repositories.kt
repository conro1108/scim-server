package com.example.scimapp.persistence

import com.example.scimapp.persistence.group.Group
import com.example.scimapp.persistence.user.User
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UserRepository: PagingAndSortingRepository<User, UUID>

interface GroupRepository: PagingAndSortingRepository<Group, UUID>
