INSERT INTO `customers` (`customer_id`, `first_name`, `surname`) VALUES (1L, 'jordan', 'harrison');
INSERT INTO `items` (`item_id`, `item_name`, `item_cost`) VALUES (1L, 'Call of Duty', 25.99);
INSERT INTO `orders`(`fk_customer_id`) VALUES (1);
-- INSERT INTO `order_items` (`item_quantity`, `fk_order_id`, `fk_item_id`) VALUES (1, 1, 1);