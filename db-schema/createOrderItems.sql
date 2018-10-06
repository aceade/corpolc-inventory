CREATE TABLE order_items(
    order_id bigint NOT NULL REFERENCES orders(orderId),
    item_id bigint NOT NULL REFERENCES items(id),
    quantity integer
);