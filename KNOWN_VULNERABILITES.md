# List of known vulnerabilties

As stated in the README, there *WILL* be vulnerabilties in this. Some will be deliberate, for educational purposes. Others will be accidental.

## Injection attacks

1. SQL injection in SiteController.


## Lack of encryption in transit

1. Access to the database is currently over a plaintext connection (over localhost)
2. The embedded Tomcat server used for testing is not configured to allow secure connections.


## Insufficient authorisation

1. SiteController
2. EmployeeController
3. ProjectController

These are being addressed.

## Insufficient authentication

1. Basic Access Authentication used to prompt for user details. This is better than nothing, especially if served over a secure connection.

## Information disclosure

1. Everywhere. Try requesting a project using a string for the ID, ie. make a GET request to /projects?id=test. You will get back a full stack trace.
