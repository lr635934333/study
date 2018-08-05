CREATE TABLE demo(
  id VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  PRIMARY KEY ( id )
);

CREATE TABLE uc_user(
  id VARCHAR (50) NOT NULL ,
  username VARCHAR (50) NOT NULL UNIQUE ,
  password VARCHAR (257) NOT null,
  email VARCHAR (50),
  phonenum VARCHAR (20),
  PRIMARY KEY (id)
)
