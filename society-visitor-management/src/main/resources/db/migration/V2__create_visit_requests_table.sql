CREATE TABLE visit_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resident_id BIGINT NOT NULL,
    visitor_name VARCHAR(255) NOT NULL,
    visitor_phone VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    valid_from DATETIME NOT NULL,
    valid_until DATETIME NOT NULL,
    checked_in_at DATETIME,
    checked_out_at DATETIME,
    CONSTRAINT fk_visit_resident FOREIGN KEY (resident_id) REFERENCES residents(id)
);