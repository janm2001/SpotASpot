CREATE TABLE IF NOT EXISTS _user(
        id UUID PRIMARY KEY DEFAULT uuid_generate_v4 (),
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