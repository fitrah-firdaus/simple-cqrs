CREATE TABLE IF NOT EXISTS student(
id INTEGER NOT NULL generated always as IDENTITY ,
name varchar(30) NOT NULL,
  CONSTRAINT primary_key PRIMARY KEY(id)
);