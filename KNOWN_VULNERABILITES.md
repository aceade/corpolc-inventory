# List of known vulnerabilties

As stated in the README, there *WILL* be vulnerabilties in this. Some will be deliberate, for educational purposes. Others will be accidental.

## Injection attacks

1. SQL injection in SiteController. This one is deliberate.
2. Possible SQL injection in root (http://localhost:8081/?query=query%22+AND+%221%22%3D%221%22+--+ ). No REST URLs use this address.


## Lack of encryption in transit

1. Access to the database is currently over a plaintext connection (over localhost)
2. The embedded Tomcat server used for testing is not configured to allow secure connections.


## Insufficient authentication

1. Basic Access Authentication used to prompt for user details. This is better than nothing, especially if served over a secure connection.
2. No auditing mechanism. This is to be addressed.

## Information disclosure

1. Everywhere. Try requesting a project using a string for the ID, ie. make a GET request to /projects?id=test. This will return a full stack trace. Found using OWASP ZAP, confirmed manually.

## Cross Site Scripting Weakness 

1. ProjectController: (http://localhost:8081/projects?id=%3Cscript%3Ealert%281%29%3B%3C%2Fscript%3E). Found using OWASP ZAP, confirmed by manual testing (blocked by NoScript)
