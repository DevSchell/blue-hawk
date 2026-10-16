-- Altera publisher.country de INT para VARCHAR. Os valores válidos são definidos pelo enum Country na aplicação.
ALTER TABLE publisher MODIFY COLUMN country VARCHAR(30) NOT NULL;
