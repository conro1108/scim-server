package com.example.scimapp.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "resource not found")
class ResourceNotFoundException: RuntimeException() {
}
