drop database spe;

create database spe;
use spe;

CREATE TABLE movie_details (
	id INT NOT NULL AUTO_INCREMENT,
	movie_name VARCHAR(255) NOT NULL,
	release_date VARCHAR(255) NOT NULL,
	ott_platforms VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    about VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE user_login (
  id INT NOT NULL AUTO_INCREMENT,
  phone_number VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);
CREATE TABLE new_releases (
 id INT NOT NULL AUTO_INCREMENT,
  phone_number VARCHAR(255) NOT NULL,
  movie_name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

insert into user_login(phone_number) values ("9704525404");


