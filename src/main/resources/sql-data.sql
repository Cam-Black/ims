INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`items` (`item_name`, `item_cost`) VALUES ("Call of Duty: Modern Warfare", 20.14);
INSERT INTO `ims`.`orders` ( `fk_customer_id`) VALUES (1);
INSERT INTO `ims`.`order_items` (`fk_order_id`, `fk_item_id`) VALUES (1, 1);