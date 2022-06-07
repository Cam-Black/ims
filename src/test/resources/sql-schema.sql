DROP TABLE customers;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `surname` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`customer_id`)
);