CREATE TABLE users (
    id serial NOT NULL PRIMARY KEY,
    name character varying(20) NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(30) NOT NULL,
    phone character varying(11) NOT NULL,
    website character varying(30),
    deleted boolean NOT NULL,
    info JSONB NOT NULL
);
CREATE TABLE posts(
    id serial NOT NULL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    title character varying(80),
    body character varying(255),
    deleted boolean NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE countries(
    id serial PRIMARY KEY NOT NULL,
    name character varying(20) NOT NULL,
    deleted boolean NOT NULL
);
CREATE TABLE provinces(
    id serial PRIMARY KEY NOT NULL,
    country_id INTEGER NOT NULL,
    name character varying(20) NOT NULL,
    CONSTRAINT fk_country FOREIGN KEY(country_id) REFERENCES countries(id) ON DELETE CASCADE ON UPDATE CASCADE,
    deleted boolean NOT NULL
);
CREATE TABLE cities(
    id serial PRIMARY KEY NOT NULL,
    province_id INTEGER NOT NULL,
    name character varying(20) NOT NULL,
    CONSTRAINT fk_province FOREIGN KEY(province_id) REFERENCES provinces(id) ON DELETE CASCADE ON UPDATE CASCADE,
    deleted boolean NOT NULL
);
CREATE TABLE addresses(
    id serial PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    city_id INTEGER NOT NULL,
    street character varying(20),
    suite character varying(20),
    complete_address character varying(200) NOT NULL,
    lat FLOAT NOT NULL,
    log FLOAT NOT NULL,
    deleted boolean NOT NULL,
    CONSTRAINT fk_city FOREIGN KEY(city_id) REFERENCES cities(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);