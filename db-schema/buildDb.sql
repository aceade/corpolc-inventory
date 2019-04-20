
-- Create various enums that the tables will require
CREATE TYPE department AS ENUM('Board', 'Finance', 'Maintenance', 'Marketing', 'PublicRelations', 'Research', 'Security', 'Shipping');
CREATE TYPE security_rating AS ENUM('MINIMUM','LOW', 'MEDIUM', 'PRIVATE', 'CONFIDENTIAL', 'HIGH', 'HIGHEST');
CREATE TYPE item_types AS ENUM('FOOD', 'ALCOHOL', 'MEDICAL_EQUIPMENT', 'TOOLS', 'OFFICE_SUPPLIES', 'WEAPONS');
CREATE TYPE resource_change_reason AS ENUM('creating', 'reading', 'updating', 'deleting');
CREATE TYPE project_status AS ENUM('PROPOSED','REJECTED','IN_PROGRESS','COMPLETED','CANCELLED');

CREATE TABLE projects (
  id serial PRIMARY KEY,
  title text,
  summary text,
  budget numeric,
  security_level security_rating,
  status project_status
);

CREATE TABLE sites (
  id serial PRIMARY KEY,
  country text,
  region text,
  address text,
  security_level security_rating
);

CREATE TABLE employees (
    id serial,
    "name" text,
    department_type department,
    workplace bigint REFERENCES sites (id),
    birthday bigint,
    salary numeric,
    security_level security_rating,
    currently_employed boolean DEFAULT true,
    CONSTRAINT id_key PRIMARY KEY(id)
);

CREATE TABLE employee_projects (
    employee_id bigint REFERENCES employees (id),
    project_id bigint REFERENCES projects (id),
    CONSTRAINT employee_project_key PRIMARY KEY (employee_id, project_id)
);

CREATE TABLE site_projects (
    site_id bigint REFERENCES sites (id),
    project_id bigint REFERENCES projects (id),
    CONSTRAINT site_project_key PRIMARY KEY (site_id, project_id)
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
    enabled boolean DEFAULT true
);

CREATE TABLE authorities(
    username text PRIMARY KEY REFERENCES users(username),
    authority text
);

CREATE TABLE orders(
    id serial PRIMARY KEY,
    "siteId" integer,
    "orderDate" date,
    username text REFERENCES users(username)
);

CREATE TABLE order_items(
    order_id bigint NOT NULL REFERENCES orders(id),
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



-- Insert default values
INSERT INTO sites (country, region, address, security_level)
VALUES	('Ireland', 'Connacht', 'Delphi, Mweelrea', 'MEDIUM'),
		('Null Island', 'Middle of Nowhere', 'The Buoy', 'LOW');
		
INSERT INTO employees ("name", department_type, workplace, birthday, salary, security_level)
VALUES	('Test User 1', 'Board', 1, 1000, 1000, 'HIGH'),
		('Test User 2', 'Research', 1, 2000, 500, 'HIGHEST');

INSERT INTO projects (title, summary, budget, security_level, status)
VALUES 	('Spionenkrabben', 'An attempt to train crabs as spies', 1000, 'MEDIUM', 'PROPOSED'),
		('Trololo', 'Singing trolls', 2000, 'HIGH', 'REJECTED');

INSERT INTO site_projects VALUES(1, 1),(2,2);
INSERT INTO employee_projects VALUES(2,1);

-- Only an idiot would have these passwords on their luggage! Or their account.
INSERT INTO users VALUES('test1', 'password', 1),
						('test2', '123456', 2);
INSERT INTO authorities VALUES('test1', 'ROLE_FULL_READONLY'), ('test2', 'ROLE_ADMIN');

INSERT INTO items(name, buying_price, selling_price, weight, consumable, "type")
VALUES  ('Mún Beer', 10, 20, 10, true, 'ALCOHOL'),
		('Bonglesticks', 10, 50, 5, false, 'TOOLS');
