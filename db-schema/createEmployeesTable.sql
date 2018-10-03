
CREATE TABLE employees (
    id serial,
    "name" text,
    title text,
    department_type department,
    birthday bigint,
    salary numeric,
    security_rating security_level,
    currently_employed boolean,
    CONSTRAINT id_key PRIMARY KEY(id)
);