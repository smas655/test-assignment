INSERT INTO limits (limit_sum, limit_datetime, limit_currency_shortname, expense_category)
VALUES (1000.00, CURRENT_TIMESTAMP, 'USD', 'product'),
       (1000.00, CURRENT_TIMESTAMP, 'USD', 'service');

INSERT INTO exchange_rates (currency_pair, rate, date)
VALUES ('KZT/USD', 450.00, CURRENT_DATE),
       ('RUB/USD', 75.00, CURRENT_DATE);