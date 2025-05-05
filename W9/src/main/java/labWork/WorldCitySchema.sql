DROP TABLE city;
DROP TABLE countries;
DROP TABLE continents;



CREATE TABLE IF NOT EXISTS continents(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS countries(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    continent_id INT NOT NULL,

    FOREIGN KEY (continent_id) REFERENCES continents(id)
);

CREATE TABLE IF NOT EXISTS city(
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_id INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    capital BOOLEAN DEFAULT FALSE,
    latitude DECIMAL(10, 7) NOT NULL,
    longidude DECIMAL(10, 7) NOT NULL,

    FOREIGN KEY (country_id) REFERENCES countries(id)
);