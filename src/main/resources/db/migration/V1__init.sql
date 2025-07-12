CREATE TABLE option
(
    id          UUID NOT NULL,
    label       VARCHAR(255),
    text        VARCHAR(255),
    question_id UUID,
    CONSTRAINT pk_option PRIMARY KEY (id)
);

CREATE TABLE question
(
    id             UUID NOT NULL,
    question_text  VARCHAR(255),
    correct_option VARCHAR(255),
    CONSTRAINT pk_question PRIMARY KEY (id)
);

ALTER TABLE option
    ADD CONSTRAINT FK_OPTION_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question (id);