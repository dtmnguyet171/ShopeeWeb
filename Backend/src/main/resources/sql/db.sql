DROP DATABASE IF EXISTS shopee;
CREATE DATABASE shopee;

use shopee;

drop table  if exists`Account`;
CREATE TABLE `Account` (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(50) not null unique,
                           `role` ENUM('CUSTOMER', 'SELLER', 'ADMIN'),
                           `password` VARCHAR(50) not null,
                           date_of_birth DATE,
                           address VARCHAR(255),
                           full_name VARCHAR(50),
                           phone_number VARCHAR(12) not null,
                           email VARCHAR(50) not null,
                           facebook VARCHAR(50),
                           information VARCHAR(255)
);

drop table if exists Product;
CREATE TABLE Product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         `name` VARCHAR(255) not null unique,
                         image VARCHAR(255) not null,
                         price INT,
                         `status` ENUM('NEW', 'OLD') not null,
                         shipping_unit ENUM('EXPRESS', 'FAST', 'SAVE') not null,
                         `type` ENUM('PHONE', 'COMPUTER', 'CLOTHES', 'FOOTWEAR') not null,
                         create_date DATE
);

drop table if exists `Order`;
CREATE TABLE `Order` (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         create_date DATE,
                         create_by INT,
                         product_id INT,
                         quantity INT,
                         `status` ENUM('PENDING', 'DONE', 'CANCEL'),
                         FOREIGN KEY (create_by)
                             REFERENCES `Account` (id),
                         FOREIGN KEY (product_id)
                             REFERENCES Product (id)
);
