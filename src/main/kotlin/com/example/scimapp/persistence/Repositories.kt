package com.example.scimapp.persistence

import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UserRepository: PagingAndSortingRepository<ScimUser, UUID>

interface GroupRepository: PagingAndSortingRepository<ScimGroup, UUID>
