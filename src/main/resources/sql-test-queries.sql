USE ims;
INSERT INTO `customers` (`first_name`, `surname`) VALUES ("George", "Washington"), ("Jack", "Sparrow");
INSERT INTO `items` (`item_name`, `item_cost`) VALUES ("CoD", 25.99), ("Witcher", 15.99);
INSERT INTO `orders` (`fk_customer_id`) VALUES (1),(2);
INSERT INTO `order_items` (`item_quantity`, `fk_order_id`, `fk_item_id`) VALUES (2, 1, 1), (1, 1, 2), (1, 2, 2);


SELECT * FROM customers;
SELECT * FROM orders ORDER BY order_id;
SELECT * FROM orders o LEFT OUTER JOIN order_items oi ON o.order_id = oi.fk_order_id;
SELECT * FROM order_ite ms ORDER BY order_items_id;
INSERT INTO order_items (item_quantity, fk_item_id, fk_order_id) VALUES (1,  (SELECT item_id FROM items WHERE item_id= 1), (SELECT order_id FROM orders WHERE order_id=1));