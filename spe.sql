drop database spe;

create database spe;
use spe;


CREATE TABLE movie_details (
	id INT NOT NULL AUTO_INCREMENT,
	movie_name VARCHAR(255) NOT NULL,
	release_date VARCHAR(255) NOT NULL,
	ott_platforms VARCHAR(255) NOT NULL,
	movie_img VARCHAR(255) NOT NULL,
	genre VARCHAR(255) NOT NULL,
	about VARCHAR(255) NOT NULL,
	PRIMARY KEY (id,movie_name)
);

CREATE TABLE user_login (
  id INT NOT NULL AUTO_INCREMENT,
  phone_number VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE mylist_details (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    movie_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE new_releases (
 id INT NOT NULL AUTO_INCREMENT,
  phone_number VARCHAR(255) NOT NULL,
  movie_name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

insert into user_login(phone_number) values ("9704525404");



insert into movie_details(movie_name,release_date,ott_platforms,movie_img, genre) values ("Racegurram","24thmarch","Netflix","https://movietitles.s3.ap-south-1.amazonaws.com/movie4.jpeg", "Comedy");
insert into movie_details(movie_name,release_date,ott_platforms,movie_img, genre) values ("RRR","25thmarch","Zee5","https://movietitles.s3.ap-south-1.amazonaws.com/movie5.jpeg", "Action");
insert into mylist_details(user_id, movie_name) values (1,"Racegurram");
insert into mylist_details(user_id, movie_name) values (1,"RRR");

