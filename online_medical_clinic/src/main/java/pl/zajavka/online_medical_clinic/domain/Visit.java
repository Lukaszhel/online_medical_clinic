package pl.zajavka.online_medical_clinic.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "visitNumber")
@ToString(of = {"visitId", "visitNumber", "dateTime", "comment", "booked", "doctor", "patient"})
public class Visit {

    Integer visitId;
    String visitNumber;
    OffsetDateTime dateTime;
    String comment;
    Boolean booked;
    Doctor doctor;
    Patient patient;

}


