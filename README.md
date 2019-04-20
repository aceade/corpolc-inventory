# CORPOLC INVENTORY SYSTEM

## About

This is a project/employee/site directory for a fictional corporation. I started this as some Spring practice, but decided to expand it as a means of teaching myself vulnerability scanning and Docker.

#### Things to note:

* It is *not* for production use!
    * There will be vulnerabilities, so I can teach myself how to scan for vulnerabilities.
    * There may be jokes, references to various memes, and other nonsense.
    * This currently only supports a Postgresql database. I do not intend to support others in the foreseeable future.
    * It runs on an embedded Tomcat server that is not set up for secure connections.


## Licence

See the LICENCE file

## Running and building

### Running the Postgresql Docker container

* Building: `sudo docker build --tag=corpolc .` 
* Running: `sudo docker run -p 5432:5432 -e POSTGRES_PASSWORD=${password} corpolc`.
* Running in background: `sudo docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=${password} corpolc`. Will print the hash required to stop the container
* Check if running: `sudo docker container ls`
* Close in background: `sudo docker container stop ${hash}`

### Building and running the application

* `mvn clean install`
* `mvn spring-boot:run -Dcom.aceade.corpolc.jdbcUser=postgres -Dcom.aceade.corpolc.jdbcPassword=${password}`

When it's running, visit localhost:8081/home.html
