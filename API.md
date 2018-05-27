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
Required roles: ROLE_FULL_ADMIN
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
Required roles: ROLE_FULL_ADMIN
```
DELETE /employees/?id=${employeeId}
Response: true|false
```

#### Get all employees
Required roles: ROLE_FULL_ADMIN,ROLE_FULL_READONLY
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
Required roles: Any. ROLE_FULL_ADMIN,ROLE_FULL_READONLY required to view full details.
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
Required roles: Any. ROLE_FULL_ADMIN, ROLE_FULL_READONLY required to view all details of any project. ROLE_PROJECT_ADMIN may view full details of a project they control
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
Required roles: ROLE_FULL_ADMIN,ROLE_FULL_READONLY
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
Required roles: ROLE_FULL_ADMIN
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
Required roles: ROLE_FULL_ADMIN,ROLE_PROJECT_ADMIN. Project Admins may only change their own project
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

#### Get a specific site
Required roles: ROLE_FULL_ADMIN, ROLE_FULL_READONLY and ROLE_VIEW_ALL_ADDRESSES required for all details.
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
Required roles: ROLE_FULL_ADMIN,ROLE_FULL_READONLY
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
Required roles: ROLE_FULL_ADMIN,ROLE_FULL_READONLY
```
GET /site/count
Response: 2


#### Add site
Required role: ROLE_FULL_ADMIN
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
Required role: ROLE_FULL_ADMIN

```
DELETE /projects?id=1
Response on success: 200 OK
Response on failure: 500 Internal Server Error
```

#### Get site using a SQL-injection prone query

Required role: none. Anybody can access this.
This exists primarily so I can teach myself how to scan for vulnerabilities. I still have not figured out how to get OWASP ZAP to recognise this.

```
GET /projects/get/with/weak/query?id=1
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
Required role: ROLE_FULL_ADMIN (all URLs)

#### Add a new user
```
POST /users
Request Body:
{
    "username" : "mbison",
    "password" : "ButForMeItWasTuesday",
    "employeeId" : 1,
    "role" : "ROLE_VIEW_OWN_DETAILS"
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

## Supply URLs
Used to place or view orders, or add/view items

#### View an item
```
GET /item?id=1
Response:
{
  "id" : 1
  "name": "First Aid Kit",
  "buyingPrice": 10.5,
  "sellingPrice": 12.2,
  "weightPerUnit": 1.0,
  "consumable": false,
  "type": "MEDICAL_EQUIPMENT"
}
```

#### Search for items
```
GET /item/search?name=First
Response:
[
    {
      "id" : 1
      "name": "First Aid Kit (Large)",
      "buyingPrice": 10.5,
      "sellingPrice": 12.2,
      "weightPerUnit": 1.0,
      "consumable": false,
      "type": "MEDICAL_EQUIPMENT"
    },
    {
      "id" : 2
      "name": "First Aid Kit (Small)",
      "buyingPrice": 10.5,
      "sellingPrice": 12.2,
      "weightPerUnit": 1.0,
      "consumable": false,
      "type": "MEDICAL_EQUIPMENT"
    }
]
```

#### Add an item
```
POST /item
{
    "name" : "Spycrab Steaks",
    "buyingPrice" : 1,
    "sellingprice" : 2
    "weightPerUnit" : 2000,
    "consumable" : true,
    "type" : FOOD    
}
Response on success: 200 OK
```

#### Add an order
The items are a Map of item IDs to quanity
```
POST /order
{
    "siteId" : 1,
    "orderDate" : 1235421653,
    "orderItems": {
        "1" : 2,
        "11" : 5
    }
}
Response on success: 200 OK
```

#### View order by ID
```
GET /order?id=1
Response on success:
{
  "id": 12,
  "items": {
    "Item{name=First Aid Kit, buyingPrice=10.5, sellingPrice=12.2, weightPerUnit=1.0, consumable=false, type=MEDICAL_EQUIPMENT}": 2
  },
  "address": {
    "id": 0,
    "country": "Null Island",
    "region": "Middle of Nowhere",
    "postalAddress": "Somewhere",
    "minimumSecurityLevel": null
  },
  "orderDate": "2018-04-19",
  "orderStatus": "SUBMITTED",
  "username": "ajensen451"
}
```

#### Change order status
```
POST /order/status
{
    "newStatus" : "APPROVED"
}
```

#### View user's orders
```
GET /order/user?username=mbison

```
[{
  "id": 11,
  "items": null,
  "address": {
    "id": 1,
    "country": "Null Island",
    "region": "Middle of Nowhere",
    "postalAddress": "Somewhere",
    "minimumSecurityLevel": null
  },
  "orderDate": "2018-04-19",
  "orderStatus": "APPROVED",
  "username": "mbison"
}, {
  "id": 200,
  "items": null,
  "address": {
    "id": 999,
    "country": "",
    "region": "Ankh-Morpork",
    "postalAddress": "The Bucket",
    "minimumSecurityLevel": null
  },
  "orderDate": "2018-04-19",
  "orderStatus": "CANCELLED",
  "username": "mbison"
}]
```

#### View my orders
```
GET /order/user/mine
Response on success:
[{
  "id": 11,
  "items": null,
  "address": {
    "id": 55,
    "country": "United States of North America",
    "region": "Detroit, MI",
    "postalAddress": "Sarif Industries",
    "minimumSecurityLevel": null
  },
  "orderDate": "2018-04-19",
  "orderStatus": "APPROVED",
  "username": "ajensen451"
}, {
  "id": 201,
  "items": null,
  "address": {
    "id": 999,
    "country": "United States of North America",
    "region": "New York",
    "postalAddress": "Liberty Island",
    "minimumSecurityLevel": null
  },
  "orderDate": "2018-04-19",
  "orderStatus": "SUBMITTED",
  "username": "ajensen451"
}]
```

#### View site stocks
```
GET /site?siteId=1
Response on success:
{
  "Item{name=First Aid Kit, buyingPrice=0.0, sellingPrice=12.2, weightPerUnit=1.0, consumable=false, type=MEDICAL_EQUIPMENT}": 5,
  "Item{name=Sewer Beer, buyingPrice=0.0, sellingPrice=54.0, weightPerUnit=10.0, consumable=true, type=ALCOHOL}": 20
}
```

## Auditing URLs
Used to view audit records. Require ROLE_FULL_ADMIN to access

#### View user audit records
```
GET /audit/user/mbison
Response on success:
{
    [{
      "timestamp": 1526139243353,
      "username": "ajensen451",
      "previousState": true
    }, {
      "timestamp": 1526139267150,
      "username": "sfisher",
      "previousState": false
    }]
}
```

#### View site audit records
```
GET /audit/site/1
Response on success:
{
    [{
      "timestamp": 1527327277675,
      "username": "philiprowlands",
      "previousCountry": "Ireland",
      "previousRegion": "Connacht",
      "previousAddress": "Mutton Island, Galway",
      "previousSecurityRating": "MINIMUM",
      "auditingReason": "creating"
    }]
}
```

#### View project audit_records
```
GET /audit/project/1
Response on success:
{
    [{
      "timestamp": 1526203198945,
      "username": "philiprowlands",
      "previousState": "IN_PROGRESS",
      "previousSummary": "Yet another test",
      "previousTitle": "Testing",
      "previousBudget": 200.0,
      "previousSecurityLevel": "MINIMUM"
    }, {
      "timestamp": 1527102247747,
      "username": "philiprowlands",
      "previousState": "IN_PROGRESS",
      "previousSummary": "Yet another test",
      "previousTitle": "Testing",
      "previousBudget": 200.0,
      "previousSecurityLevel": "MINIMUM"
    }]
}
```

#### View employee audit_records
```
GET /audit/employee/1
Response on success:
{
}
```
