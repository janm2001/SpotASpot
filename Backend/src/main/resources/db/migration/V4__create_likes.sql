CREATE TABLE IF NOT EXISTS likes(
    user_id UUID CONSTRAINT user_fk REFERENCES _user(id),
    event_id BIGINT CONSTRAINT event_fk REFERENCES event(id),
    PRIMARY KEY (user_id, event_id)
);