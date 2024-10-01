CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              account_from VARCHAR(10) NOT NULL,
                              account_to VARCHAR(10) NOT NULL,
                              currency_shortname VARCHAR(3) NOT NULL,
                              sum DECIMAL(10, 2) NOT NULL,
                              expense_category VARCHAR(50) NOT NULL,
                              datetime TIMESTAMP WITH TIME ZONE NOT NULL,
                              limit_exceeded BOOLEAN
);

CREATE TABLE limits (
                        id BIGSERIAL PRIMARY KEY,
                        limit_sum DECIMAL(10, 2) NOT NULL,
                        limit_datetime TIMESTAMP WITH TIME ZONE NOT NULL,
                        limit_currency_shortname VARCHAR(3) NOT NULL,
                        expense_category VARCHAR(50) NOT NULL
);

CREATE TABLE exchange_rates (
                                id BIGSERIAL PRIMARY KEY,
                                currency_pair VARCHAR(7) NOT NULL,
                                rate DECIMAL(10, 4) NOT NULL,
                                date DATE NOT NULL
);