USE ims;

INSERT INTO customers (first_name, surname) VALUES ("George", "Washington"), ("Nelson", "Mandela");
INSERT INTO items (item_name, item_cost) VALUES ("Cod", 35.99), ("Witcher", 21.13);
INSERT INTO orders (fk_customer_id) VALUES (1), (2);
INSERT INTO order_items (item_quantity, fk_order_id, fk_item_id) VALUES (1, 1, 1), (1,  2, 2);