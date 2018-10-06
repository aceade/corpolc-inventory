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