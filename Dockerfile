# Create the DB, mainly so I don't need to install it every time

## Derived from https://stackoverflow.com/questions/34751814/build-postgres-docker-container-with-initial-schema
FROM postgres

ENV POSTGRES_DB connacht_trading
COPY db-schema/buildDb.sql /docker-entrypoint-initdb.d/

# Run the application now
