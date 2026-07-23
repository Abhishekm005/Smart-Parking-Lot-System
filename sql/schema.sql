-- ===========================================
-- Smart Parking Lot Database Schema
-- ===========================================

CREATE TABLE vehicles (
                          vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          vehicle_type ENUM('MOTORCYCLE','CAR','BUS') NOT NULL,
                          vehicle_number VARCHAR(50) NOT NULL UNIQUE,
                          owner_name VARCHAR(150),
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parking_floors (
                                floor_id INT PRIMARY KEY,
                                floor_name VARCHAR(100) NOT NULL
);

CREATE TABLE parking_lots (
                              parking_lot_id PRIMARY KEY,
                              floor_id INT NOT NULL,
                              lot_label VARCHAR(50),                    -- human label like "1st-101"
                              lot_type ENUM('MOTORCYCLE','CAR','BUS') NOT NULL,
                              available TINYINT(1) NOT NULL DEFAULT 1,
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_parking_lots_floor FOREIGN KEY (floor_id) REFERENCES parking_floors(floor_id) ON DELETE RESTRICT
);

-- index to quickly find an available lot by type
CREATE INDEX idx_lot_type_available ON parking_lots(lot_type, available);

CREATE TABLE parking_tickets (
                                 parking_ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 vehicle_id BIGINT NOT NULL,
                                 parking_lot_id BIGINT NOT NULL,
                                 entry_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 exit_time DATETIME NULL,
                                 parking_fee DECIMAL(10,2) DEFAULT 0,
                                 status ENUM('ACTIVE','PAID') NOT NULL DEFAULT 'ACTIVE',
                                 CONSTRAINT fk_ticket_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE,
                                 CONSTRAINT fk_ticket_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(parking_lot_id) ON DELETE RESTRICT
);

CREATE INDEX idx_ticket_vehicle ON parking_tickets(vehicle_id);
CREATE INDEX idx_ticket_status ON parking_tickets(status);

CREATE TABLE payments (
                          payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          parking_ticket_id BIGINT NOT NULL,
                          amount DECIMAL(10,2) NOT NULL,
                          payment_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_payment_ticket FOREIGN KEY (parking_ticket_id) REFERENCES parking_tickets(parking_ticket_id) ON DELETE CASCADE
);