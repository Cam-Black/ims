DROP SCHEMA IF EXISTS ims;

CREATE SCHEMA ims;

DROP TABLE IF EXISTS `ims`.`order_items`;
DROP TABLE IF EXISTS `ims`.`orders`;
DROP TABLE IF EXISTS `ims`.`customers`;
DROP TABLE IF EXISTS `ims`.`items`;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customer_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `surname` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`item_id` INT AUTO_INCREMENT,
    `item_name` VARCHAR(50)  NOT NULL,
    `item_cost` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`order_id` INT AUTO_INCREMENT,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `ims`.`customers` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
	`order_items_id` INT AUTO_INCREMENT,
    `item_quantity` INT DEFAULT 1,
    `fk_order_id` INT NOT NULL,
    `fk_item_id` INT NOT NULL,
    PRIMARY KEY (`order_items_id`),
    FOREIGN KEY (`fk_order_id`) REFERENCES `ims`.`orders`(`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_item_id`) REFERENCES `ims`.`items`(`item_id`) ON DELETE CASCADE
);