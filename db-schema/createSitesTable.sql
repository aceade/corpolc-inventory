CREATE TABLE sites (
  id serial PRIMARY KEY,
  country text,
  region text,
  address text,
  security_level security_rating
);