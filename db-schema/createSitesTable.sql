-- Remove later
CREATE TYPE security_rating AS ENUM('MINIMUM', 'LOW', 'MEDIUM', 'PRIVATE', 
    'CONFIDENTIAL','HIGH','HIGHEST');

CREATE TABLE sites (
  id serial,
  country text,
  region text,
  address text,
  security_level security_rating
);