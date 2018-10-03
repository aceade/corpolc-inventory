CREATE TABLE site_stocks (
    site_id bigint REFERENCES sites(id),
    item_id bigint REFERENCES items(id),
    quantity int
);