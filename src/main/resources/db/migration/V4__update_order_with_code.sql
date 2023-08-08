-- Add the new 'code' column to the ORDERS table
ALTER TABLE ORDERS
    ADD COLUMN code VARCHAR(255) NOT NULL UNIQUE;