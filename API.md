# API

This lists the API for convenience.

## Employee URLS

Manipulating employee data.


#### Get Employee
```
GET /employees/?id=${employeeId}
Response:
{
    "id" : 1,
    "name" : "Baron Mac Aughley",
    "birthday" : "1890-11-22",
    "department" : "Board",
    "workplace" : {
        "id":1,
        "country":"Ireland",
        "region":"Connacht",
        "postalAddress":"Eyre House, Galway",
        "minimumSecurityLevel":"LOW"
    },
    "clearanceLevel":"HIGH",
    "salary":1200.0
}
```

#### Add Employee
```
POST /employees/
Request Body:
{
    "name" : "Mr Bean",
    "departmentId" : 1,
    "birthday" : "1900-12-15"
    "siteId" : 1,
    "securityLevel" : 2,
    "salary" : 200.00
}
Response: true|false
```

#### Terminate Employee
```
DELETE /employees/?id=${employeeId}
Response: true|false
```

#### Get all employees
```
GET /employees/all
Response Body:
{
    [
        {
            "id" : 1,
            "name" : "Baron Mac Aughley",
            "birthday" : "1890-11-22",
            "department" : "Board",
            "workplace" : {
                "id":1,
                "country":"Ireland",
                "region":"Connacht",
                "postalAddress":"Eyre House, Galway",
                "minimumSecurityLevel":"LOW"
            },
            "clearanceLevel":"HIGH",
            "salary":1200.0
        },
        {
            "id" : 2,
            "name" : "Captain Kelvinator",
            "birthday" : "1923-12-22",
            "department" : "Marketing",
            "workplace" : {
                "id":1,
                "country":"Null Island",
                "region":"Middle of Nowhere",
                "postalAddress":"Word of Turscar Marketing LLC",
                "minimumSecurityLevel":"HIGH"
            },
            "clearanceLevel":"HIGH",
            "salary":600.00
        },
    ]
}
```

#### Get projects for employee
```
GET /employees/projects?id=${employeeId}
Response Body:
{
    [
        {
            "id":0,
            "securityLevel":"MEDIUM",
            "title":"Spionenkrabben",
            "summary":"Attemping to train crabs as spies",
            "budget":null,
            "sites":null,
            "employees":null
        }
    ]
}
```

## Project URLS

Manipulating project data

#### Get project by ID

```
GET /projects?id=1
Response Body:
{
    "id": 0,
    "securityLevel": "MEDIUM",
    "title": "Spionenkrabben",
    "summary": "Attemping to train crabs as spies",
    "budget": 100000,
    "status": "IN_PROGRESS",
    "sites": [
        {
            "id": 1,
            "country": "Ireland",
            "region": "Connacht",
            "postalAddress": "Connacht Trading Company, Delphi, Mweelrea",
            "minimumSecurityLevel": "MEDIUM"
        }
    ],
    "employees": [
        {
            "id": 1,
            "name": "Baron Mac Aughley",
            "birthday": "1890-11-22",
            "department": "Board",
            "workplace": {
                "id": 1,
                "country": null,
                "region": null,
                "postalAddress": null,
                "minimumSecurityLevel": null
            },
            "clearanceLevel": "HIGH",
            "salary": 1200,
            "currentlyEmployed": true
        }
    ]
}
```

#### Get all projects

```
GET /projects/all
Response Body:
[
    {
        "id": 0,
        "securityLevel": "MEDIUM",
        "title": "Spionenkrabben",
        "summary": "Attemping to train crabs as spies",
        "budget": 100000,
        "status": "IN_PROGRESS",
        "sites": null,
        "employees": null
    },
    {
        "id": 0,
        "securityLevel": "MINIMUM",
        "title": "Testing",
        "summary": "Test insertion",
        "budget": 10,
        "status": "REJECTED",
        "sites": null,
        "employees": null
    }
]
```

#### Add project

```
POST /projects
Request Body:
{
    "title": "Testing",
    "summary": "Test insertion",
    "budget": 10,
    "securityLevel" : [1-7]
}

ResponseBody:
{
    "success" : false|true,
    "newResourceId" : 4,
    "responseText" : "New resource added"
}

```

#### Change project status

```
POST /projects/status
RequestBody:
{
    "projectId" : 2,
    "newProjectStatus" : IN_PROGRESS|CANCELLED|REJECTED|COMPLETED
}
ResponseBody:
{
    "success" : false    
}
```

## Site URLs

Manipulating site details

```
GET /sites?id=1
Response Body:
{
    "id": 1,
    "country": "Ireland",
    "region": "Connacht",
    "postalAddress": "Connacht Trading Company, Delphi, Mweelrea",
    "minimumSecurityLevel": "MEDIUM"
}
```

#### Get all projects

```
GET /sites/all
Response Body:
[
    {
        "id": 1,
        "country": "Ireland",
        "region": "Connacht",
        "postalAddress": "Connacht Trading Company, Delphi, Mweelrea",
        "minimumSecurityLevel": "MEDIUM"
    },
    {
        "id": 2,
        "country": "Middle of Nowhere",
        "region": "Null Island",
        "postalAddress": "The Buoy",
        "minimumSecurityLevel": "MINIMUM"
    }
]
```

#### Get site count
```
GET /site/count
Response: 2


#### Add site
```
POST /projects
Request Body:
{
    "country": "Ireland",
    "region": "Connacht",
    "postalAddress": "Connacht Trading Company, Delphi, Mweelrea",
    "minimumSecurityLevel": [0-7]
}
```

Response on success: 200 OK
Response on failure: 500 Internal Server Error

```

#### Delete Site

```
DELETE /projects?id=1
Response on success: 200 OK
Response on failure: 500 Internal Server Error
```

#### Get site using a SQL-injection prone query

This exists primarily so I can teach myself how to scan for vulnerabilities.

```
GET /projects/get/with/weak/query?id=aaa
Happy path response:
{
    "id": 1,
    "country": "Ireland",
    "region": "Connacht",
    "postalAddress": "Connacht Trading Company, Delphi, Mweelrea",
    "minimumSecurityLevel": "MEDIUM"
}
```

## User URLs

Used to add or disable users

#### Add a new user
```
POST /users
Request Body:
{
    "username" : "mbison",
    "password" : "ButForMeItWasTuesday",
    "employeeId" : 1
}
Response on success: true (200 OK)
Response on failure: false (200 OK)
```

#### Disable a user
```
DELETE /users/{username}
Response on success: true (200 OK)
Response on failure: false (200 OK)

```

