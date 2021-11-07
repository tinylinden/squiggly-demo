Feature: Squiggly Demo API (v1) - GET /issue

  Scenario: 001 - All fields are returned if fields filter is not provided
    Then GET '/issue' should return
      """
      {
        "id":"ISSUE-1",
        "issueSummary":"Dragons Need Fed",
        "issueDetails":"I need my dragons fed pronto.",
        "reporter":{
          "firstName":"Daenerys",
          "lastName":"Targaryen"
        },
        "assignee":{
          "firstName":"Jorah",
          "lastName":"Mormont"
        },
        "actions":[
          {
            "type":"COMMENT",
            "text":"I'm going to let Daario get this one.",
            "user":{
              "firstName":"Jorah",
              "lastName":"Mormont"
            }
          },
          {
            "type":"CLOSE",
            "text":"All set.",
            "user":{
              "firstName":"Daario",
              "lastName":"Naharis"
            }
          }
        ],
        "properties":{
          "priority":"1",
          "email":"motherofdragons@got.com"
        }
      }
      """

  Scenario: 002 - Only 'id' is returned when fields filter is provided
    Then GET '/issue' with 'id' fields filter should return
      """
      {
        "id": "ISSUE-1"
      }
      """

  Scenario: 003 - All but 'actions' and 'properties' is returned when filter is provided
    Then GET '/issue' with '-actions,-properties' fields filter should return
      """
      {
        "id":"ISSUE-1",
        "issueSummary":"Dragons Need Fed",
        "issueDetails":"I need my dragons fed pronto.",
        "reporter":{
          "firstName":"Daenerys",
          "lastName":"Targaryen"
        },
        "assignee":{
          "firstName":"Jorah",
          "lastName":"Mormont"
        }
      }
      """

  Scenario: 004 - Only 'id' and 'action.type' is returned when filter is provided
    Then GET '/issue' with 'id,actions[type]' fields filter should return
      """
      {
        "id":"ISSUE-1",
        "actions":[
          {
            "type":"COMMENT"
          },
          {
            "type":"CLOSE"
          }
        ]
      }
      """