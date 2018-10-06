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