CREATE TABLE doctor
(
    doctor_id         SERIAL       NOT NULL,
    name              VARCHAR(32)  NOT NULL,
    surname           VARCHAR(32)  NOT NULL,
    specialization    VARCHAR(32)  NOT NULL,
    pwz               VARCHAR(7)   NOT NULL,
    phone             VARCHAR(32)  NOT NULL,
    email             VARCHAR(32)  NOT NULL,
    doctor_address_id INT          NOT NULL,
    PRIMARY KEY (doctor_id),
    UNIQUE (email, pwz),
    CONSTRAINT fk_doctor_doctor_address
        FOREIGN KEY (doctor_address_id)
            REFERENCES doctor_address (doctor_address_id)
);