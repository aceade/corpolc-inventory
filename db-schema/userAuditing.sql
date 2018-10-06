CREATE TABLE user_auditing(
    uid text REFERENCES users(username),
    username text,
    previous_state boolean,
    last_changed bigint,
    PRIMARY KEY(uid, last_changed)
);