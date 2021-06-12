package com.example.scimapp

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "resource not found")
class ResourceNotFoundException: RuntimeException() {
}
