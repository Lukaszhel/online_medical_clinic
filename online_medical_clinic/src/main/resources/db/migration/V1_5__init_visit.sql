CREATE TABLE visit
(
    visit_id              SERIAL                     NOT NULL,
    visit_number          VARCHAR(64)                NOT NULL,
    date_time             TIMESTAMP WITH TIME ZONE   NOT NULL,
    comment TEXT,
    booked                BOOLEAN                    NOT NULL,
    doctor_id             INT                        NOT NULL,
    patient_id            INT,
    PRIMARY KEY (visit_id),
    UNIQUE (visit_number),
    CONSTRAINT fk_visit_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctor (doctor_id),
     CONSTRAINT fk_visit_patient
        FOREIGN KEY (patient_id)
            REFERENCES patient (patient_id)

);