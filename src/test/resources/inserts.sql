DELETE FROM schedules;

INSERT INTO schedules (id, scheduled_date_time, recipient, message, communication_type) VALUES
('123e4567-e89b-12d3-a456-426614174000', '2025-01-06T13:59:00', '1', '1', 'SMS'),
('123e4567-e89b-12d3-a456-426614174001', '2025-01-06T13:59:00', '2', '2', 'EMAIL'),
('123e4567-e89b-12d3-a456-426614174002', '2025-01-06T13:59:00', '3', '3', 'WHATSAPP'),
('123e4567-e89b-12d3-a456-426614174003', '2025-01-06T13:59:00', '4', '4', 'PUSH');