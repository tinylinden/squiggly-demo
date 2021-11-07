package pl.tinylinden.squigglydemo.adapters.rest.v1

import pl.tinylinden.squigglydemo.adapters.rest.v1.model.ActionDto
import pl.tinylinden.squigglydemo.adapters.rest.v1.model.IssueDto
import pl.tinylinden.squigglydemo.adapters.rest.v1.model.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class IssueEndpoint : IssueApi {

    override fun findIssue(fields: String?): ResponseEntity<IssueDto> =
        ResponseEntity.ok(issue())

    private fun issue(): IssueDto = IssueDto(
        id = "ISSUE-1",
        issueSummary = "Dragons Need Fed",
        issueDetails = "I need my dragons fed pronto.",
        reporter = UserDto(
            firstName = "Daenerys",
            lastName = "Targaryen"
        ),
        assignee = UserDto(
            firstName = "Jorah",
            lastName = "Mormont"
        ),
        actions = listOf(
            ActionDto(
                type = "COMMENT",
                text = "I'm going to let Daario get this one.",
                user = UserDto(
                    firstName = "Jorah",
                    lastName = "Mormont"
                )
            ),
            ActionDto(
                type = "CLOSE",
                text = "All set.",
                user = UserDto(
                    firstName = "Daario",
                    lastName = "Naharis"
                )
            )
        ),
        properties = mapOf(
            "priority" to "1",
            "email" to "motherofdragons@got.com"
        )
    )
}