# DB SCHEMA

This file lists the database schema. This currently uses a Postgresql 9.3 database. The most recent version will be contained in the buildDb.sql file.

## Enums/types

* department: ['Board', 'Finance', 'Maintenance', 'Marketing', 'PublicRelations', 'Production', 'Research', 'Security', 'Shipping']
* security_rating: ['MINIMUM','LOW', 'MEDIUM', 'PRIVATE', 'CONFIDENTIAL', 'HIGH', 'HIGHEST']
* item_types: ['FOOD', 'ALCOHOL', 'MEDICAL_EQUIPMENT', 'TOOLS', 'OFFICE_SUPPLIES', 'WEAPONS']
* resource_change_reason: ['creating', 'reading', 'updating', 'deleting']
* project_status: ['PROPOSED','REJECTED','IN_PROGRESS','COMPLETED','CANCELLED']

## Employees


| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id            | serial    | Primary key. Autoincremented |
| name          | text      | Employee's full name |
| title          | text      | Employee's full name |
| birthday      | long      | UTC timestamp |
| department_type    | department | What branch do they work for |
| workplace     | integer   | ID for the site where they work |
| security_level | security_rating   | Their clearance level. See the security_rating enum |
| salary        | numeric   | Salary per annum |
| currently_employed       | boolean   | Do they currently work for us or not? |

## Employee-projects

Acts as a mapping between the employes and projects tables.

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| employee_id    | bigint    | Mapped to the id column in employees |
| project_id     | bigint    | Mapped to the id column in projects  |

## Projects

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id            | serial   | Primary key. |
| title         | text      | The project's name |
| summary       | text      | What the project is about |
| budget        | numeric   | How much is being spent on this project in total |
| security_level | integer   | Minimum clearance level for all employees working on this |
| status | project_status | Can be PROPOSED, CANCELLED, REJECTED, IN_PROGRESS, or COMPLETE |


## Sites

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| ID            | serial   | Primary key. Autoincremented |
| country       | text      | E.g. Ireland |
| region        | text      | E.g. Connacht |
| address | text      | The postal address. May or may not include a postcode |
| security_level | integer   | Minimum security level for any employees working here |

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
id serial PRIMARY KEY,
  name text,
  buying_price numeric,
  selling_price numeric,
  weight numeric,
  consumable boolean,
  type item_types

Tracks items for supply purposes

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id            | serial    | Primary key |
| name          | text      | |
| buying_price  | numeric   | How much it costs the company to buy |
| selling_price | numeric   | How much we sell it for |
| weight        | numeric   | Weight per unit in kg |
| consumable    | boolean   | Can you eat it? |
| type          | item_types      | See the item_types enum |

## Orders

Tracks orders to a site

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id       | serial   | Primary key. Autoincremented |
| site        | integer   | Identifies the site to which they are being shipped |
| date     | date      | When this was placed |
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
| employee    | bigint    | Mapped to the ID column in the employees table |
| enabled       | boolean   | If set to true, the user may log in. Otherwise, they may not |


## Authorities

Used to define the user's role

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| username      | text      | Username. Must match that in the user table |
| authority     | text      | Matches a role, e.g. ROLE_FULL_ADMIN, ROLE_EDIT_OWN_DETAILS |


## Test

Used purely to test SQL injection, e.g. by passing in a command to truncate or add to this.

| Column        | Type      | Comments  |
|---------------|-----------|-----------|
| id            | integer   | A unique integer |
| name          | text      | Any string, e.g. foo |

## Employee Auditing

Tracks when and who changed an employee. Primary key is a combination of employee_id and last_changed

| Column            | Type      | Comments  |
|-------------------|-----------|-----------|
| employee_id       | bigint    | ID of the employee.  Mapped to the ID column in employees |
| last_changed      | bigint    | UTC timestamp for when this was last changed |
| username          | text      | Who changed it |
| previous_name     | text      | What their name was previously |
| previous_birthday | date      | Previous value |
| previous_workplace | bigint      | ID of the site where they previously worked |
| previous_salary   | numeric   | How much they were previously paid per annum |
| previous_security_level | integer | Previous clearance level |
| previously_current | boolean | Are they returning after a period of absence, etc? |

## Site Auditing

Tracks when and who changed/viewed a site. Primary key is a combination of site_id and last_accessed

| Column            | Type      | Comments  |
|-------------------|-----------|-----------|
| site_id           | bigint    | Identifies the site. Mapped to the ID column in sites |
| username          | text      | Who changed or accessed it |
| last_accessed     | bigint    | UTC timestamp for when it was last changed |
| previous_country  | text      | Previous country, just in case it appears in a new one due to political upheaval |
| previous_region   | text      | In case of political upheavals or administrative reasons |
| previous_address  | text      | The previous postal address |
| previous_security_rating  | integer | What security level it had previously |
| change_reason     | enum      | Why it changed. Values are creating, reading, upating, deleting |

## Project Auditing

Tracks when and who changed a project. Primary key is a combination of project_id and last_changed

| Column            | Type      | Comments  |
|-------------------|-----------|-----------|
| project_id        | bigint    | Mapped to the ID column in projects |
| username          | text      | Who changed the project |
| last_changed      | bigint    | UTC timestamp for when it changed |
| previous_status   | enum      | As per the status column in projects |
| previous_summary  | text      | |
| previous_title    | text      | |
| previous_budget   | numeric   | |
| previous_security_rating | integer | |

## User Auditing

Tracks when and who changed a user

| Column            | Type      | Comments  |
|-------------------|-----------|-----------|
| uid               | text      | The user's ID. Mapped to the username column in the users table |
| username          | text      | Who changed it |
| previous_state    | boolean   | Previously enabled (true) or disabled (false) |
| last_changed      | bigint    | UTC timestamp for when this last changed |
