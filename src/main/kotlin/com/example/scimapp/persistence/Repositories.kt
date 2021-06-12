package com.example.scimapp.persistence

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository: PagingAndSortingRepository<ScimUser, Long> {
}

interface GroupRepository: PagingAndSortingRepository<ScimGroup, Long> {

}