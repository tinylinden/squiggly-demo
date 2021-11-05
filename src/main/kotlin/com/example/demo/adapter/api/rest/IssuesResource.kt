package com.example.demo.adapter.api.rest

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/issues"], produces = [MediaType.APPLICATION_JSON_VALUE])
class IssuesResource {

    @GetMapping
    fun get(): Issue = Issue.DRAGONS_NEED_FED
}
