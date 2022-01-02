DELETE FROM TACO_ORDER_TACOS;
DELETE FROM TACO_INGREDIENTS;
DELETE FROM TACO;
DELETE FROM TACO_ORDER;
DELETE FROM INGREDIENT;

INSERT INTO INGREDIENT (id, name, type) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO INGREDIENT (id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO INGREDIENT (id, name, type) VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO INGREDIENT (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO INGREDIENT (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO INGREDIENT (id, name, type) VALUES ('LETC', 'Lettuce', 'WRAP');
INSERT INTO INGREDIENT (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO INGREDIENT (id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO INGREDIENT (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO INGREDIENT (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');

INSERT INTO TACO (id, created_at, name) VALUES (1, '2021-08-21 00:00:00.000', 'Taco No.1');
INSERT INTO TACO (id, created_at, name) VALUES (2, '2021-08-21 00:00:01.000', 'Taco No.2');
INSERT INTO TACO (id, created_at, name) VALUES (3, '2021-08-21 00:00:02.000', 'Taco No.3');
INSERT INTO TACO (id, created_at, name) VALUES (4, '2021-08-21 00:00:03.000', 'Taco No.4');

INSERT INTO TACO_INGREDIENTS (taco_id, ingredients_id) VALUES (1, 'FLTO');
INSERT INTO TACO_INGREDIENTS (taco_id, ingredients_id) VALUES (1, 'COTO');
INSERT INTO TACO_INGREDIENTS (taco_id, ingredients_id) VALUES (2, 'GRBF');
INSERT INTO TACO_INGREDIENTS (taco_id, ingredients_id) VALUES (2, 'CARN');

--INSERT INTO TACO_ORDER (id, cccvv, cc_expiration, cc_number, created_at, delivery_city,
--delivery_name, delivery_state, delivery_street, delivery_zip, user_id)
--VALUES (1, 'CARN');
