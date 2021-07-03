package com.example.scimapp.persistence

import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UserRepository: PagingAndSortingRepository<User, UUID>

interface GroupRepository: PagingAndSortingRepository<Group, UUID>
