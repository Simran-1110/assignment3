-- Drop table if it exists to ensure a clean setup on each run
DROP TABLE IF EXISTS items;

-- Create the items table with appropriate constraints
CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    -- Timestamps for auditing
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- A check constraint to ensure data integrity for the item type
    CONSTRAINT chk_item_type CHECK (type IN ('RAW', 'MANUFACTURED', 'IMPORTED'))
);