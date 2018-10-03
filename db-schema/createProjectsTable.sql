CREATE TYPE project_status AS ENUM('PROPOSED', 'CANCELLED', 'REJECTED', 'IN_PROGRESS','COMPLETE');

-- This will need to be shared
CREATE TYPE security_rating AS ENUM('MINIMUM', 'LOW', 'MEDIUM', 'PRIVATE', 
    'CONFIDENTIAL','HIGH','HIGHEST');

CREATE TABLE projects (
  id serial,
  title text,
  summary text,
  budget numeric,
  security_level security_rating,
  status project_status
);