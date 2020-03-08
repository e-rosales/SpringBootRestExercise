DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	currency VARCHAR(10) NOT NULL,
	money DECIMAL NOT NULL,
	treasury BOOLEAN
);