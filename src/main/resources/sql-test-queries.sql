USE ims;
INSERT INTO `customers` (`first_name`, `surname`) VALUES ("George", "Washington"), ("Jack", "Sparrow");
INSERT INTO `items` (`item_name`, `item_cost`) VALUES ("CoD", 25.99), ("Witcher", 15.99);
INSERT INTO `orders` (`fk_customer_id`) VALUES (1),(2);
INSERT INTO `order_items` (`item_quantity`, `fk_order_id`, `fk_item_id`) VALUES (2, 1, 1), (1, 1, 2), (1, 2, 2);


SELECT * FROM customers;
SELECT * FROM orders ORDER BY order_id;
SELECT * FROM orders o LEFT OUTER JOIN order_items oi ON o.order_id = oi.fk_order_id LEFT OUTER JOIN items i ON i.item_id = oi.fk_item_id;
SELECT * FROM order_items ORDER BY fk_order_id, order_items_id;
SELECT o.order_id, c.customer_id, c.first_name, c.surname, i.item_id, i.item_name, i.item_cost, oi.item_quantity FROM orders o LEFT OUTER JOIN order_items oi ON o.order_id = oi.fk_order_id LEFT OUTER JOIN items i ON i.item_id = oi.fk_item_id LEFT OUTER JOIN customers c ON c.customer_id = o.fk_customer_id  ORDER BY order_id, order_items_id;
INSERT INTO order_items (item_quantity, fk_item_id, fk_order_id) VALUES (1,  (SELECT item_id FROM items WHERE item_id= 1), (SELECT order_id FROM orders WHERE order_id=2));
UPDATE order_items SET item_quantity = item_quantity-1 WHERE item_quantity >= 0 AND fk_item_id = 2 AND fk_order_id = 1;
DELETE FROM order_items WHERE item_quantity = 0;
SELECT * FROM order_items LEFT OUTER JOIN items ON items.item_id = order_items.fk_item_id;

INSERT INTO order_items (item_quantity, fk_item_id, fk_order_id) VALUES (1, 1, 1);

DELETE FROM order_items WHERE item_quantity = 0;

UPDATE order_items SET item_quantity = item_quantity - 1 WHERE item_quantity >= 0 AND fk_item_id = 1 AND fk_order_id = 1 ORDER BY order_items_id DESC LIMIT 1;

SELECT *
FROM order_items
WHERE order_items_id=(
    SELECT max(order_items_id) FROM order_items
    );