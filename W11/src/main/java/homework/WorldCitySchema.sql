DROP TABLE city;
DROP TABLE countries;
DROP TABLE continents;



CREATE TABLE continents(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE countries(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    continent_id INT NOT NULL,

    FOREIGN KEY (continent_id) REFERENCES continents(id)
);

CREATE TABLE city(
    id BIGSERIAL PRIMARY KEY,
    country_id INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    capital BOOLEAN DEFAULT FALSE,
    latitude DECIMAL(10, 7),
    longitude DECIMAL(10, 7),

    FOREIGN KEY (country_id) REFERENCES countries(id)
);

INSERT INTO continents (name) VALUES
                                      ( 'Europe'),
                                      ( 'Asia'),
                                      ( 'North America');

-- Insert countries
INSERT INTO countries (name, code, continent_id) VALUES
                                                         ( 'Germany', 'DEU', 1),
                                                         ( 'France', 'FRA', 1),
                                                         ( 'Japan', 'JPN', 2);

-- Insert cities
INSERT INTO city (name, capital, latitude, longitude, country_id) VALUES
                                                                        ('Berlin', true, 52.5200, 13.4050, 1),
                                                                        ('Munich', false, 48.1351, 11.5820, 1),
                                                                        ('Paris', true, 48.8566, 2.3522, 2);
