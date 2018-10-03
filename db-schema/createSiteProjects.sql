
CREATE TABLE site_projects (
    site_id bigint REFERENCES sites (id),
    project_id bigint REFERENCES projects (id)
);