CREATE TABLE items (
  id serial PRIMARY KEY,
  name text,
  buying_price numeric,
  selling_price numeric,
  weight numeric,
  consumable boolean,
  "type" item_types
);