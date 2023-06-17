CREATE TABLE IF NOT EXISTS rating(
    user_id UUID CONSTRAINT user_fk REFERENCES _user(id),
    event_id BIGINT CONSTRAINT event_fk REFERENCES event(id),
    stars INT NOT NULL,
    PRIMARY KEY (user_id, event_id)
);