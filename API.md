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
DELETE /employees/?id=${employeeId}
Response: true|false

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

#### Check if current user is admin (DEPRECATED)
GET /employees/isAdmin
Return: true|false
