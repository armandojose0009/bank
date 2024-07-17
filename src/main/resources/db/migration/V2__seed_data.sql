INSERT INTO card (card_number, holder_name, expiry_date, active, blocked, balance)
VALUES
('1234567890123456', 'John Doe', '2026-12-31', true, false, 1000.00),
('2345678901234567', 'Jane Smith', '2027-06-30', true, false, 500.00),
('3456789012345678', 'Bob Johnson', '2028-03-31', true, false, 200.00);

INSERT INTO transaction (card_id, amount, transaction_date, annulled)
VALUES
('1234567890123456', 50.00, '2024-07-15 10:30:00', false),
('1234567890123456', 75.00, '2024-07-16 14:45:00', false),
('2345678901234567', 100.00, '2024-07-17 09:20:00', false),
('3456789012345678', 30.00, '2024-07-18 16:10:00', false);
