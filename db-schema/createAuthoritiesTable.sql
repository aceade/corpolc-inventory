CREATE TABLE authorities(
    username text PRIMARY KEY REFERENCES users(username),
    authority text
);