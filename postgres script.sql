CREATE DATABASE ics499
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS starsystem (
	id int NOT NULL,
	name VARCHAR(255),
	population bigint,
	government_type VARCHAR(255),
	economy int,
	space_resource int,
	location int[]
);

CREATE TABLE IF NOT EXISTS universe (
	id int,
	shape VARCHAR(255),
	size int
);

CREATE TABLE IF NOT EXISTS PLANET (
	id int,
	name VARCHAR(255),
	size int,
	population bigint,
	economy int,
	natural_resource int,
	location int[]
);

INSERT INTO PLANET 
VALUES (1, ' Earth', 35, 7000000000, 8, 10, '{50,50}')


INSERT INTO PLANET
VALUES (2, 'Mars', 40, 0, 0, 5, '{52,50}');


INSERT INTO starsystem 
VALUES (1, 'starsystem 1', 2500000000, 'type 1', 6, 3, '{23, 12}');

INSERT INTO starsystem
VALUES (2, 'starsystem 2', 150000, 'type 2', 5, 8, '{44, 68}');
