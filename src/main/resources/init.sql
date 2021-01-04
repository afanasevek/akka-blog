CREATE TABLE IF NOT EXISTS users(
    user_id BIGSERIAL NOT NULL,
    is_moderator BOOLEAN NOT NULL,
    reg_time TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    photo TEXT,
    CONSTRAINT users_pk PRIMARY KEY (user_id));
CREATE TABLE IF NOT EXISTS posts(
    post_id BIGSERIAL NOT NULL,
    is_active BOOLEAN NOT NULL,
    moderation_status VARCHAR(20) NOT NULL,
    moderation_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    time TIMESTAMP NOT NULL,
    title VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    view_count INTEGER NOT NULL,
    CONSTRAINT posts_pk PRIMARY KEY (post_id),
    FOREIGN KEY (moderation_id) REFERENCES users(user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id));
CREATE TABLE IF NOT EXISTS post_votes(
    vote_id BIGSERIAL NOT NULL,
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    time TIMESTAMP NOT NULL,
    value BOOLEAN NOT NULL,
    CONSTRAINT votes_pk PRIMARY KEY (vote_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id));
CREATE TABLE IF NOT EXISTS tags(
    tag_id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT tags_pk PRIMARY KEY (tag_id));
CREATE TABLE IF NOT EXISTS tag2post(
    id BIGSERIAL NOT NULL,
    post_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    CONSTRAINT tag2post_pk PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (tag_id) REFERENCES tags(tag_id));
CREATE TABLE IF NOT EXISTS post_comments(
    comment_id BIGSERIAL NOT NULL,
    parent_id BIGINT,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    time TIMESTAMP NOT NULL,
    text TEXT NOT NULL,
    CONSTRAINT comment_pk PRIMARY KEY (comment_id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id));
CREATE TABLE IF NOT EXISTS global_settings(
    settings_id BIGSERIAL NOT NULL,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    CONSTRAINT settings_pk PRIMARY KEY (settings_id));
