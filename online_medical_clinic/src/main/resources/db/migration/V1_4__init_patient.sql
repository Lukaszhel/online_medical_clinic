CREATE TABLE patient
(
    patient_id         SERIAL                     NOT NULL,
    name               VARCHAR(32)                NOT NULL,
    surname            VARCHAR(32)                NOT NULL,
    pesel              VARCHAR(11)                NOT NULL,
    phone              VARCHAR(32)                NOT NULL,
    email              VARCHAR(32)                NOT NULL,
    date_of_birth      DATE                       NOT NULL,
    patient_address_id INT                        NOT NULL,
    PRIMARY KEY (patient_id),
    UNIQUE (pesel),
    CONSTRAINT fk_patient_patient_address
        FOREIGN KEY (patient_address_id)
            REFERENCES patient_address (patient_address_id)
);