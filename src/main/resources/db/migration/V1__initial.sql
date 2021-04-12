CREATE TABLE users (
    id serial NOT NULL PRIMARY KEY,
    name character varying(20) NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(50) NOT NULL,
    phone character varying(30) NOT NULL,
    website character varying(255),
    deleted boolean NOT NULL,
    info JSONB NOT NULL
);
CREATE TABLE posts(
    id serial NOT NULL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    title character varying(255),
    body character varying(255),
    deleted boolean NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE countries(
    id serial PRIMARY KEY NOT NULL,
    name character varying(50) NOT NULL
);

CREATE TABLE addresses(
    id serial PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    country_id INTEGER NOT NULL,
    street character varying(30),
    suite character varying(30),
    complete_address character varying(200) NOT NULL,
    lat FLOAT NOT NULL,
    log FLOAT NOT NULL,
    deleted boolean NOT NULL,
    CONSTRAINT fk_country FOREIGN KEY(country_id) REFERENCES countries(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);