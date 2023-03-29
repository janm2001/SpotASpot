CREATE SEQUENCE event_seq INCREMENT 1;
CREATE TABLE event(
        id BIGSERIAL PRIMARY KEY,
        name TEXT NOT NULL,
        description TEXT,
        category TEXT NOT NULL,
        city TEXT,
        location TEXT NOT NULL,
        date_time TIMESTAMPTZ NOT NULL,
        is_available BOOLEAN NOT NULL,
        created_by BIGINT CONSTRAINT user_fk REFERENCES _user(id),
        created_at TIMESTAMPTZ NOT NULL,
        last_change TIMESTAMPTZ NOT NULL
);