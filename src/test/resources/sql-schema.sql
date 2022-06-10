DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `surname` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT AUTO_INCREMENT,
    `item_name` VARCHAR(50)  DEFAULT NULL,
    `item_cost` DECIMAL(10, 2) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT AUTO_INCREMENT,
    `fk_customer_id` INT DEFAULT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `order_items` (
	`order_items_id` INT AUTO_INCREMENT,
    `item_quantity` INT DEFAULT NULL,
    `fk_order_id` INT NOT NULL,
    `fk_item_id` INT DEFAULT NULL,
    PRIMARY KEY (`order_items_id`),
    FOREIGN KEY (`fk_order_id`) REFERENCES `orders`(`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_item_id`) REFERENCES `items`(`item_id`) ON DELETE CASCADE
);