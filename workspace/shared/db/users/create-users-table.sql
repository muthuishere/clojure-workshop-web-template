CREATE TABLE users
(email VARCHAR(30) PRIMARY KEY,
 first_name VARCHAR(30),
 last_name VARCHAR(30),
 admin BOOLEAN,
 last_login TIMESTAMP,
 is_active BOOLEAN,
 pass VARCHAR(300));