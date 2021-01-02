create table if not exists users(
    user_id VARCHAR(255) NOT NULL,
    is_moderator BOOLEAN NOT NULL,
    reg_time TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    photo TEXT,
    CONSTRAINT users_pk PRIMARY KEY (user_id));
