CREATE TABLE site_auditing(
    site_id bigint REFERENCES sites(id),
    last_changed bigint,
    username text,
    previous_country text,
    previous_region text,
    previous_address text,
    previous_security_rating security_rating,
    change_reason resource_change_reason,
    PRIMARY KEY (site_id, last_changed)
);