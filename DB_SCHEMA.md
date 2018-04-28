# DB SCHEMA

This file lists the database schema. This currently uses a Postgresql 9.3 database.

## Employees

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| ID            | bigint    | Primary key. Should really be a serial |
| name          | text      | Employee's full name |
| birthday      | date      |  |
| department    | integer   | Should probably be an enum |
| workplace     | integer   | ID for the site where they work |
| securityLevel | integer   | Ranges from 0 (minimum) to 7 (maximum). Should really be an enum |
| salary        | numeric   | Salary per annum |
| current       | boolean   | Do they currently work for us or not? |

## Employee-projects

Acts as a mapping between the employes and projects tables.

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| employeeId    | bigint    | Mapped to the ID column in employees |
| projectId     | bigint    | Mapped to the ID column in projects  |

## Projects

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| ID            | integer   | Primary key. Should really be a serial |
| title         | text      | The project's name |
| summary       | text      | What the project is about |
| budget        | numeric   | How much is being spent on this project |
| securityRating     | integer   | Minimum clearance level for all employees working on this |
| status | enum | Can be PROPOSED, CANCELLED, REJECTED, IN_PROGRESS, or COMPLETE |


## Sites

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| ID            | integer   | Primary key. Should really be a serial |
| country       | text      |  |
| region        | text      |  |
| postalAddress | text      |  |
| securityLevel | integer   | Minimum security level for any employees working here |

## Sites-projects

Acts as a mapping between the sites and projects tables

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| site_id       | bigint    | Mapped to the ID column in sites |
| project_id    | bigint    | Mapped to the ID column in projects  |

## Site_stocks

Tracks the supplies currently at a site

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| site_id       | bigint    | ID of the site. Mapped to the ID column in sites |
| item_id       | bigint    | ID of the item. Mapped to the id column in items |
| quantity      | integer   | How much we currently have in stock |

## Items

Tracks items for supply purposes

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id            | serial    | Primary key |
| name          | text      | |
| buying_price  | numeric   | How much it costs the company to buy |
| selling_price | numeric   | How much we sell it for |
| weight        | numeric   | Weight per unit in kg |
| consumable    | boolean   | Can you eat it? |
| type          | enum      | Can be FOOD, ALCOHOL, MEDICAL_EQUIPMENT, TOOLS, OFFICE_SUPPLIES, WEAPONS |

## Orders

Tracks orders to a site

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| orderId       | integer   | Primary key. Autoincremented |
| siteId        | integer   | Identifies the site to which they are being shipped |
| orderDate     | date      | When this was placed |
| username      | text      | Who placed the order. Mapped to username in users table |

## Order-items

Keeps track of the items in an order

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| order_id      | long      | Mapped to the orderID column in orders. Must not be NULL |
| item_id       | long      | Mapped to the id column in items. Must not be NULL |
| quantity      | integer   | How many units of the item are included |

## Users

Handles login details

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| username      | text      | Unique username |
| password      | text      | Salted and hashed using BCrypt |
| employeeId    | bigint    | Mapped to the ID column in the employees table |
| enabled       | boolean   | If set to true, the user may log in. Otherwise, they may not |


## Authorities

Used to define the user's role

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| username      | text      | Username. Must match that in the user table |
| authority     | text      | Matches a role, e.g. FULL_ADMIN, EDIT_OWN_DETAILS |


## Test

Used purely to test SQL injection.

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id            | integer   | A unique integer |
| name          | text      | Any string, e.g. foo |
