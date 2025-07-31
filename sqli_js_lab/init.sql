DELETE FROM drink;
DROP TABLE IF EXISTS drink;

CREATE TABLE IF NOT EXISTS drink (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    stock INT NOT NULL CHECK (stock >= 0),
    rating DECIMAL(2,1) NOT NULL CHECK (rating BETWEEN 0 AND 5)
);

INSERT INTO drink (name, price, stock, rating) VALUES
('Coca-Cola', 1.50, 50, 4.5),
('Pepsi', 1.45, 40, 4.3),
('Sprite', 1.30, 25, 4.0),
('Fanta', 1.60, 35, 4.2),
('Mountain Dew', 1.80, 15, 4.6);


-- procedure example
CREATE OR REPLACE PROCEDURE get_one_drink(
    IN p_name VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE drink
    SET stock = stock - 1
    WHERE name = p_name;
END;
$$;

-- dynamic vulnerable procedure example
CREATE OR REPLACE PROCEDURE get_one_drink_vulnerable(
    IN p_name VARCHAR
)
LANGUAGE plpgsql
AS $$
DECLARE
    query TEXT;
BEGIN
    query := 'UPDATE drink SET stock = stock - 1 WHERE name = ''' || p_name || '''';
    EXECUTE query;
END;
$$;
