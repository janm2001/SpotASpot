CREATE TABLE IF NOT EXISTS event(
        id BIGSERIAL PRIMARY KEY,
        name TEXT NOT NULL,
        description TEXT,
        category TEXT NOT NULL,
        city TEXT,
        location TEXT NOT NULL,
        image_id VARCHAR(36) UNIQUE,
        date_time TIMESTAMPTZ NOT NULL,
        is_available BOOLEAN NOT NULL,
        created_by UUID CONSTRAINT user_fk REFERENCES _user(id),
        created_at TIMESTAMPTZ NOT NULL,
        last_change TIMESTAMPTZ NOT NULL
);
ALTER SEQUENCE event_id_seq INCREMENT 1;