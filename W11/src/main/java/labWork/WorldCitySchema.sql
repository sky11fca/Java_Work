DROP TABLE city;
DROP TABLE countries;
DROP TABLE continents;



CREATE TABLE continents(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE countries(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    name VARCHAR(20) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    continent_id INT NOT NULL,

    FOREIGN KEY (continent_id) REFERENCES continents(id)
);

CREATE TABLE city(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    country_id INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    capital BOOLEAN DEFAULT FALSE,
    latitude DECIMAL(10, 7) NOT NULL,
    longidude DECIMAL(10, 7) NOT NULL,

    FOREIGN KEY (country_id) REFERENCES countries(id)
);