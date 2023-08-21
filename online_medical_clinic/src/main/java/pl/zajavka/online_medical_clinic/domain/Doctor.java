package pl.zajavka.online_medical_clinic.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "pwz")
@ToString(of = {"doctorId", "name", "surname", "specialization", "pwz", "phone", "email"})
public class Doctor {

    Integer doctorId;
    String name;
    String surname;
    String specialization;
    String pwz;
    String phone;
    String email;
    DoctorAddress doctorAddress;
    Set<Visit> visitSet;
}
