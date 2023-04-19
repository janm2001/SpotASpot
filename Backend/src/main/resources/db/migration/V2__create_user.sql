CREATE TABLE _user(
        id BIGSERIAL PRIMARY KEY,
        first_name TEXT NOT NULL,
        last_name TEXT NOT NULL,
        email TEXT NOT NULL UNIQUE,
        username TEXT NOT NULL UNIQUE,
        password TEXT NOT NULL,
        role TEXT NOT NULL,
        created_at TIMESTAMPTZ NOT NULL,
        last_login TIMESTAMPTZ NOT NULL,
        last_change TIMESTAMPTZ NOT NULL
);
ALTER SEQUENCE _user_id_seq INCREMENT 1;