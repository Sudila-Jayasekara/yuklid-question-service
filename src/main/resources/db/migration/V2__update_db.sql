ALTER TABLE option
    ADD is_correct BOOLEAN;

ALTER TABLE option
    ALTER COLUMN is_correct SET NOT NULL;

ALTER TABLE question
DROP
COLUMN correct_option;

ALTER TABLE option
DROP
COLUMN label;