CREATE TABLE IF NOT EXISTS comment(
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    created_by UUID CONSTRAINT user_fk REFERENCES _user(id),
    event_id BIGINT CONSTRAINT event_fk REFERENCES event(id),
    reply_to BIGINT CONSTRAINT comment_fk REFERENCES comment(id),
    created_at TIMESTAMPTZ NOT NULL,
    last_change TIMESTAMPTZ NOT NULL
);
ALTER SEQUENCE comment_id_seq INCREMENT 1;