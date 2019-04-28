
-- Create various enums that the tables will require
CREATE TYPE department AS ENUM('Board', 'Finance', 'Maintenance', 'Marketing', 'PublicRelations', 'Production', 'Research', 'Security', 'Shipping');
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
    employee bigint REFERENCES employees(id),
    enabled boolean DEFAULT true
);

CREATE TABLE authorities(
    username text PRIMARY KEY REFERENCES users(username),
    authority text
);

CREATE TABLE orders(
    id serial PRIMARY KEY,
    site integer,
    date bigint,
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
		('Null Island', 'Middle of Nowhere', 'The Buoy', 'LOW'),
		('Amity', 'New York', 'USA', 'MINIMUM');
		
INSERT INTO employees ("name", title, department_type, workplace, birthday, salary, security_level)
VALUES	('Graf Bernd Otto von Schtickschtock', 'CEO', 'Board', 1, 1000, 1000, 'HIGH'),
		('Adam Jensen', 'CSO', 'Board', 1, 2000, 750, 'HIGH'),
		('Patton Ffrench', 'Chief Legal Officer', 'Board', 1, 3000, 900, 'HIGH'),
		('Aoife Mannion', 'CFO', 'Board', 1, 4000, 850, 'HIGH'),
		('Freya Bjornsson', 'CMO', 'Board', 1, 5000, 850, 'HIGH')
		('Samuel Fisher', 'Head of Security', 'Security', 1, 6000, 850, 'HIGHEST')
		('Sam Quint', 'Skipper', 'Transport', 3, 7000, 100, 'LOW')
		('Albrecht Zweistein', 'Researcher (Physics)', 'Research', 1, 8000, 500, 'CONFIDENTIAL'),
		('Pauline Bean', 'Maintenance Programmer', 'Maintenance', 2, 9000, 300, 'LOW'),
		('Edward Seagoon', 'Rumourmonger', 'Marketing', 2, 1000, 300, 'LOW');

INSERT INTO projects (title, summary, budget, security_level, status)
VALUES 	('Spionenkrabben', 'An attempt to train crabs as spies', 1000, 'MEDIUM', 'PROPOSED'),
		('Trololo', 'Singing trolls', 2000, 'HIGH', 'REJECTED'),
		('Carcharodonian lasers', 'Sharks with frickin laser beams!', 3000, 'MEDIUM', 'IN_PROGRESS'),
		('Project FTW', 'Dwarven Magma Cannons', 20000, 'HIGH', 'REJECTED'),
		('New Coffee Machines', 'Coffee machines for offices', 300, 'LOW', 'COMPLETED');

INSERT INTO site_projects VALUES(1, 1),(2,2);
INSERT INTO employee_projects VALUES(2,1);

-- Spring Security 4.2.3 requires the $2a$ encoding - see https://github.com/spring-projects/spring-security/issues/3320
-- The sample passwords here are, respectively:
-- password, INeverAskedForThis, 12345, goldgoldgold, ThankCrunchieItsFriday, iwasneverhere, IHateSharks, e=mchammer, brillant and ShutUpEccles
-- Don't use these in production - use a damn password manager!
INSERT INTO users VALUES('bvonschtick', '$2a$10$cTC1OQQq85HOeM4/OFsBr.clr6sbmCwX3JqCKldmqB/xINNpnk6EW', 1),
						('ajensen451', '$2a$10$WD6WppxGrkTbZGARoE01DeKgy5aKcl5aECprNKUDENf2UVmTKYQ2y', 2),
						('pffrench', '$2a$10$hse6AFAQ8hMHSMFL5yPRFe/dRnGGUHtARUQfKYUuqIboc7.3Ywk9i', 3),
						('amannion', '$2a$10$UJ5OZEIwkM8fuWLhKStm1OkEZkuHnVH6hf/O.2KK6NPd5p1kVdYme', 4),
						('fbjornsson1', '$2a$10$CJAq2vXK9B9c3bEWdhCxMewH/4HapG5xHypUEFFhhkh4TQsBr/G6W', 5),
						('sfisher', '$2a$10$B0hZ8QPYtA.3bql6.JQaqO0XkfOLuQqEX4pHnyRh/hKxc7KPOjGwC', 6),
						('squint2', '$2a$10$tHbzU7mLArYK55jjoo/tteg6tRpBPrPUDYblcEaKXUOZY1QQlycrW', 7),
						('albrechtzweistein', '$2a$10$dh67QonOjhbLqGHZQVdJsO.vSPtY9jsv1juW5WZKpTHgD2o4VBaFG', 8),
						('pbean', '$2a$10$gQz0Yiy89HnqVIoWsN3pP.vg7dSaeYEWj8Ya8aKkzhSiOMmJwZkK.', 9),
						('nseagoon', '$2a$10$p22.Hh5Bl4XH1B8Fw8MNZOEF07GyGNP/vUz1sO9tI9HQSAxxlqEGS', 10);
INSERT INTO authorities VALUES	('bvonschtick', 'ROLE_FULL_READONLY'),
								('ajensen451', 'ROLE_FULL_READONLY'),
								('pffrench', 'ROLE_FULL_READONLY'),
								('amannion', 'ROLE_FULL_READONLY'),
								('fbjornsson1', 'ROLE_FULL_READONLY'),
								('sfisher', 'ROLE_FULE_ADMIN'),
								('squint2', 'ROLE_VIEW_ALL_ADDRESSES'),
								('albrechtzweistein', 'ROLE_PROJECT_ADMIN'),
								('pbean', 'ROLE_VIEW_OWN_DETAILS'),
								('nseagoon', 'ROLE_SITE_ADMIN');

INSERT INTO items(name, buying_price, selling_price, weight, consumable, "type")
VALUES  ('Mún Beer', 10, 20, 10, true, 'ALCOHOL'),
		('Bonglesticks', 10, 50, 5, false, 'TOOLS');

-- add a basic order
INSERT INTO orders  (site, date, username)
VALUES				(1, 3000, 'test1');
INSERT INTO order_items VALUES (1, 1, 10), (1, 2, 5);
