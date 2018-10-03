CREATE TABLE users(
    username text PRIMARY KEY UNIQUE,
    password text,
    "employeeId" bigint REFERENCES employees(id),
    enabled boolean
);