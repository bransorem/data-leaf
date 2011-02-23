DROP TABLE IF EXISTS User;
CREATE TABLE User (
 usr_id MEDIUMINT NOT NULL AUTO_INCREMENT,
 first_name VARCHAR(200),
 last_name VARCHAR(200) NOT NULL,
 alias VARCHAR(200) NOT NULL,
 access TINYINT NOT NULL,
 title VARCHAR(200),
 password VARCHAR(500) NOT NULL,
 website VARCHAR(500),
 email VARCHAR(500),
 PRIMARY KEY (usr_id)
);

DROP TABLE IF EXISTS Family;
CREATE TABLE Family (
 fam_id SMALLINT NOT NULL,
 family VARCHAR(100) NOT NULL,
 PRIMARY KEY (fam_id)
);

DROP TABLE IF EXISTS Genus;
CREATE TABLE Genus (
 genus_id SMALLINT NOT NULL,
 fam_id SMALLINT NOT NULL,
 genus VARCHAR(100) NOT NULL,
 PRIMARY KEY (genus_id),
 FOREIGN KEY (fam_id) REFERENCES Family(fam_id)
);

DROP TABLE IF EXISTS Subject;
CREATE TABLE Subject (
 sub_id MEDIUMINT NOT NULL AUTO_INCREMENT,
 genus_id SMALLINT NOT NULL,
 species VARCHAR(100) NOT NULL,
 common_name VARCHAR(200) NOT NULL,
 jepson_link VARCHAR(500) NOT NULL,
 photo BLOB NOT NULL,
 lifeform VARCHAR(100) NOT NULL,
 range_low SMALLINT NOT NULL,
 range_high SMALLINT NOT NULL,
 PRIMARY KEY (sub_id),
 FOREIGN KEY (genus_id) REFERENCES Genus(genus_id)
);

DROP TABLE IF EXISTS Observation;
CREATE TABLE Observation (
 obs_id INT NOT NULL AUTO_INCREMENT,
 subject MEDIUMINT NOT NULL,
 author MEDIUMINT NOT NULL,
 date DATETIME NOT NULL,
 quantity TINYINT NOT NULL,
 notes VARCHAR(1000),
 loc_lat FLOAT(24),
 loc_long FLOAT(24),
 loc_elevation SMALLINT,
 loc_description VARCHAR(1000) NOT NULL,
 PRIMARY KEY (obs_id),
 FOREIGN KEY (author) REFERENCES User(usr_id),
 FOREIGN KEY (subject) REFERENCES Subject(sub_id)
);

