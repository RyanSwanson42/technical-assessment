DROP TABLE IF EXISTS orders;

CREATE TABLE orders(
	orderid INT AUTO_INCREMENT(1000, 1) PRIMARY KEY,
	first_name VARCHAR(250) NOT NULL,
  	last_name VARCHAR(250) NOT NULL,
  	grade VARCHAR(250) NOT NULL,
  	state VARCHAR(250) NOT NULL,
  	city VARCHAR(250) NOT NULL,
  	address VARCHAR(250) NOT NULL,
  	zip VARCHAR(5) NOT NULL
);

INSERT INTO orders(first_name, last_name, grade, state, city, address, zip)
VALUES('John', 'Smith', 'Kindergarten', 'New York', 'Washington DC', '1600 Pennsylvania Avenue', '11239');

