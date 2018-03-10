# DB SCHEMA

This file lists the database schema, in case you want to test this yourself.

## Employees

| Column        | Type      | Comments |
|---------------|-----------|-----------|
| ID            | bigint    | Primary key. Should really be a serial |
| name          | text      | Employee's full name |
| birthday      | date      |  |
| department    | integer   | Should probably be an enum |
| workplace     | integer   | ID for the site where they work |
| securityLevel | integer   | Ranges from 0 (minimum) to 7 (maximum). Should really be an enum |
| salary        | numeric   | Salary per annum |
| current       | boolean   | Do they currently work for us or not? |


## Projects

| Column        | Type      | Comments |
|---------------|-----------|-----------|
| ID            | integer   | Primary key. Should really be a serial |
| title         | text      | The project's name |
| summary       | text      | What the project is about |
| budget        | numeric   | How much is being spent on this project |
| securityRating     | integer   | Minimum clearance level for all employees working on this |
| status | enum | Can be PROPOSED, CANCELLED, REJECTED, IN_PROGRESS, or COMPLETE |


## Sites

| Column        | Type      | Comments |
|---------------|-----------|-----------|
| ID            | integer   | Primary key. Should really be a serial |
| country       | text      |  |
| region        | text      |  |
| postalAddress | text      |  |
| securityLevel | integer   | Minimum security level for any employees working here |

## Users

|---------------|-----------|-----------|
| username      | text      | Unique username |
| password      | text      | Salted and hashed using BCrypt |
| employeeId    | bigint    | Mapped to the ID column in the employees table |
| enabled       | boolean   | If set to true, the user may log in. Otherwise, they may not |
