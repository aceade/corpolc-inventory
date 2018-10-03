
CREATE TABLE employee_projects (
    employee_id bigint REFERENCES employees (id),
    project_id bigint REFERENCES projects (id)
);