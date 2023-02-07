create database if not exists ics499;
use ics499;

create table if not exists starsystem (
    name varchar(255) not null,
    star_type varchar(255) not null,
    population int not null,
    government_type varchar(255) not null,
    economy_level int not null,
    space_resource int not null
);

create table if not exists universe (
	id int not null,
	shape varchar(255) not null
);

create table if not exists planet (
	id int not null,
	name varchar(255) not null,
    planet_type varchar(255) not null,
	planet_desc varchar(255) not null,
    population int not null,
    government_type varchar(255) not null,
    economy_level int not null,
    natural_resource int not null
);

insert into planet values (1, "earth", "rocky planet", "our planet", 7000, "democracy", 10, 8);
insert into planet values (2, "mars", "rocky planet", "mars planet", 0, "no government", 0, 2);

