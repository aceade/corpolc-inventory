CREATE DATABASE corpolcInventory;

-- Create various enums that the tables will require
CREATE TYPE department AS ENUM('Board', 'Finance', 'Maintenance', 'Marketing', 'PublicRelations', 'Research', 'Security', 'Shipping');
CREATE TYPE security_rating AS ENUM('MINIMUM','LOW', 'MEDIUM', 'PRIVATE', 'CONFIDENTIAL', 'HIGH', 'HIGHEST');
CREATE TYPE item_types AS ENUM('FOOD', 'ALCOHOL', 'MEDICAL_EQUIPMENT', 'TOOLS', 'OFFICE_SUPPLIES', 'WEAPONS');
CREATE TYPE resource_change_reason AS ENUM('creating', 'reading', 'updating', 'deleting');
CREATE TYPE project_status AS ENUM('PROPOSED','REJECTED','IN_PROGRESS','COMPLETED','CANCELLED');

CREATE TABLE projects (
  id serial,
  title text,
  summary text,
  budget numeric,
  security_level security_rating,
  status project_status
);

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

CREATE TABLE sites (
  id serial PRIMARY KEY,
  country text,
  region text,
  address text,
  security_level security_rating
);

CREATE TABLE items (
  id serial PRIMARY KEY,
  name text,
  buying_price numeric,
  selling_price numeric,
  weight numeric,
  consumable boolean,
  "type" item_types
);

CREATE TABLE users(
    username text PRIMARY KEY UNIQUE,
    password text,
    "employeeId" bigint REFERENCES employees(id),
    enabled boolean
);

CREATE TABLE authorities(
    username text PRIMARY KEY REFERENCES users(username),
    authority text
);

CREATE TABLE orders(
    "orderId" serial PRIMARY KEY,
    "siteId" integer,
    "orderDate" date,
    username text REFERENCES users(username)
);

CREATE TABLE order_items(
    order_id bigint NOT NULL REFERENCES orders(orderId),
    item_id bigint NOT NULL REFERENCES items(id),
    quantity integer
);

CREATE TABLE site_stocks (
    site_id bigint REFERENCES sites(id),
    item_id bigint REFERENCES items(id),
    quantity int
);

-- this one is used for vulnerability testing
CREATE TABLE test (
  id serial PRIMARY KEY,
  string text
);

-- auditing - keep track of what changes, when, and whodunnit
CREATE TABLE employee_auditing(
    employee_id bigint REFERENCES employees(id),
    last_changed bigint,
    username text,
    previous_name text,
    previous_birthday date,
    previous_workplace bigint,
    previous_salary numeric,
    previous_security_level security_rating,
    previously_current boolean,
    PRIMARY KEY (employee_id, last_changed)
);
CREATE TABLE project_auditing(
    project_id bigint REFERENCES projects(id),
    last_changed bigint,
    username text,
    previous_status project_status,
    previous_summary text,
    previous_title text,
    previous_budget numeric,
    previous_security_rating security_rating,
    PRIMARY KEY(project_id, last_changed)
);
CREATE TABLE site_auditing(
    site_id bigint REFERENCES sites(id),
    last_changed bigint,
    username text,
    previous_country text,
    previous_region text,
    previous_address text,
    previous_security_rating security_rating,
    change_reason resource_change_reason,
    PRIMARY KEY (site_id, last_changed)
);
CREATE TABLE user_auditing(
    uid text REFERENCES users(username),
    username text,
    previous_state boolean,
    last_changed bigint,
    PRIMARY KEY(uid, last_changed)
);
