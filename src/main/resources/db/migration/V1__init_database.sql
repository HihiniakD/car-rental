DROP SCHEMA IF EXISTS CarRental;
CREATE SCHEMA IF NOT EXISTS CarRental DEFAULT CHARACTER SET utf8 ;
USE CarRental ;

-- -----------------------------------------------------
-- Table CarRental.role
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRental.role (
                                              id INT NOT NULL AUTO_INCREMENT,
                                              name VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX name_UNIQUE (`name` ASC) VISIBLE,
    UNIQUE INDEX idrole_UNIQUE (`id` ASC) VISIBLE)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table TaxiService.user
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRental.user (
                                              id INT NOT NULL AUTO_INCREMENT,
                                              name VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE,
    INDEX fk_user_role1_idx (`role_id` ASC) VISIBLE,
    CONSTRAINT fk_user_role1
    FOREIGN KEY (`role_id`)
    REFERENCES CarRental.role (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CarRental.city
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS CarRental.city (
                                              id INT NOT NULL AUTO_INCREMENT,
                                              name VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table CarRental.car
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS CarRental.car (
                                             id INT NOT NULL AUTO_INCREMENT,
                                             model VARCHAR(45) NOT NULL,
    passengers INT NOT NULL,
    price INT NOT NULL,
    status VARCHAR(45) NOT NULL,
    transmission VARCHAR(45) NOT NULL,
    category VARCHAR(45) NOT NULL,
    city_id INT NOT NULL,
    image_url VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE,
    CONSTRAINT fk_car_city
    FOREIGN KEY (`city_id`)
    REFERENCES CarRental.city (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table CarRental.order
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS CarRental.order (
                                               id INT NOT NULL AUTO_INCREMENT,
                                               user_id INT NOT NULL,
                                               car_id INT NOT NULL,
                                               city_id INT NOT NULL,
                                               pickup_date DATE NOT NULL,
                                               dropoff_date DATE NOT NULL,
                                               total_price INT NOT NULL,
                                               status VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE,
    INDEX fk_order_user1_idx (`user_id` ASC) VISIBLE,
    INDEX fk_order_car1_idx (`car_id` ASC) VISIBLE,
    CONSTRAINT fk_order_user1
    FOREIGN KEY (`user_id`)
    REFERENCES CarRental.user (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_order_car1
    FOREIGN KEY (`car_id`)
    REFERENCES CarRental.car (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_order_city
    FOREIGN KEY (`city_id`)
    REFERENCES CarRental.city (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- Create roles
insert into carrental.role (name) values('user'), ('admin');

-- Create cities
insert into carrental.city (name) values('Kyiv'), ('Kharkiv'), ('Lviv');

-- Create users
insert into carrental.user (name, password, email, phone, role_id) values('Dmytro', 'Qwerty90', 'email@gmail.com', '464748383', 1), ('Dima', 'Qwerty90', 'email2@gmail.com', '464748385', 2);

-- Kyiv cars
insert into carrental.car (model, passengers, price, status, transmission, category, city_id, image_url)
values('2014 Nissan Rogue SV', 5, 35, 'available', 'automatic', 'SUV', 1, 'https://www.kimballstock.com/pix/car/p/06/aut-50-iz0994-01p.jpg'),
      ('2015 Jeep Grand Cherokee Limited', 5, 55, 'available', 'manual', 'SUV', 1, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz2751-01p.jpg'),
      ('2011 Audi R8 Spyder V10 Convertible', 3, 185, 'available', 'automatic', 'luxury', 1, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0207-01p.jpg'),
      ('2015 Lexus ES 350', 4, 25, 'available', 'automatic', 'standard', 1, 'https://www.kimballstock.com/pix/car/p/06/aut-50-iz0707-01p.jpg'),
      ('2013 Porsche Panamera Turbo', 4, 120, 'available', 'automatic', 'luxury', 1, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0286-01p.jpg'),

-- Kharkiv cars
      ('2015 Cadillac Escalade AWD Luxury', 5, 80, 'available', 'manual', 'SUV', 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz2515-01p.jpg'),
      ('2015 Lexus RX 350', 4, 50, 'available', 'automatic', 'SUV', 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz2849-01p.jpg'),
      ('BMW i8 Hybrid/Electric', 3, 250, 'available', 'automatic', 'luxury', 2, 'https://www.kimballstock.com/pix/car/p/01/aut-01-rk0351-01p.jpg'),
      ('2015 Mercedes Benz C-Class C300 Sport Sedan', 4, 60, 'available', 'automatic', 'standard', 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0462-01p.jpg'),
      (' 2015 Audi A7', 4, 90, 'available', 'automatic', 'luxury', 2, 'https://www.kimballstock.com/pix/car/p/05/aut-48-iz0073-01p.jpg'),

-- Lviv cars
      ('2013 Audi Q7 TDI Premium', 4, 70, 'available', 'automatic', 'SUV', 3, 'https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg'),
      ('2015 Lexus LX 570', 4, 70, 'available', 'manual', 'SUV', 3, 'https://www.kimballstock.com/pix/car/p/06/aut-50-iz0743-01p.jpg'),
      ('2014 Porsche 911 Turbo', 3, 150, 'available', 'automatic', 'luxury', 3, 'https://www.kimballstock.com/pix/car/p/07/por-03-rk0145-01p.jpg'),
      ('2015 Hyundai Accent GLS 6', 4, 25, 'available', 'manual', 'standard', 3, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0385-01p.jpg'),
      ('2012 Chevrolet Corvette Grand Sport', 3, 140, 'available', 'manual', 'luxury', 3, 'https://www.kimballstock.com/pix/car/p/07/vet-01-rk1092-01p.jpg');