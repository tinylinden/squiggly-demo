package com.example.demo.adapter.api.rest

data class Issue(
    val id: String,
    val issueSummary: String,
    val issueDetails: String,
    val reporter: User,
    val assignee: User,
    val actions: List<Action>,
    val properties: Map<String, String>
) {
    companion object {
        val DRAGONS_NEED_FED = Issue(
            id = "ISSUE-1",
            issueSummary = "Dragons Need Fed",
            issueDetails = "I need my dragons fed pronto.",
            reporter = User(
                firstName = "Daenerys",
                lastName = "Targaryen"
            ),
            assignee = User(
                firstName = "Jorah",
                lastName = "Mormont"
            ),
            actions = listOf(
                Action(
                    type = "COMMENT",
                    text = "I'm going to let Daario get this one.",
                    user = User(
                        firstName = "Jorah",
                        lastName = "Mormont"
                    )
                ),
                Action(
                    type = "CLOSE",
                    text = "All set.",
                    user = User(
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
}

data class User(
    val firstName: String,
    val lastName: String
)

data class Action(
    val type: String,
    val text: String,
    val user: User
)
