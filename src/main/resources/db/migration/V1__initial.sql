CREATE TABLE users (
    id serial NOT NULL PRIMARY KEY,
    name character varying(20) NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(50) NOT NULL,
    phone  character varying(30) NOT NULL,
    website character varying(255)
);

CREATE TABLE posts(
    id serial NOT NULL PRIMARY KEY ,
    userid INTEGER NOT NULL,
    title character varying(255),
    body character varying(255)
);
