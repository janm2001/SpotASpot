CREATE SEQUENCE _user_seq INCREMENT 1;
CREATE TABLE _user(
                      id BIGSERIAL PRIMARY KEY,
                      first_name TEXT NOT NULL,
                      last_name TEXT NOT NULL,
                      email TEXT NOT NULL UNIQUE,
                      username TEXT NOT NULL UNIQUE,
                      password TEXT NOT NULL,
                      role TEXT NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      last_login TIMESTAMP NOT NULL
);