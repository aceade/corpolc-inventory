CREATE TABLE orders(
    "orderId" serial PRIMARY KEY,
    "siteId" integer,
    "orderDate" date,
    username text REFERENCES users(username)
);