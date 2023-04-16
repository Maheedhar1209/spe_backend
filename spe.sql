drop database spe;

create database spe;
use spe;

CREATE TABLE movie_details (
	id INT NOT NULL AUTO_INCREMENT,
	movie_name VARCHAR(255) NOT NULL,
	release_date VARCHAR(255) NOT NULL,
	ott_platforms VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);






insert into movie_details(movie_name,release_date,ott_platforms) values ("Racegurram","24thmarch","Netflix");

