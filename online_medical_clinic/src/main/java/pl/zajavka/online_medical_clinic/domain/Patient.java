package pl.zajavka.online_medical_clinic.domain;

import lombok.*;

import java.util.Date;
import java.util.Set;
@With
@Value
@Builder
@EqualsAndHashCode(of = "pesel")
@ToString(of = {"patientId", "name", "surname", "pesel", "phone", "email", "dateOfBirth"})
public class Patient {

    Integer patientId;
    String name;
    String surname;
    String pesel;
    String phone;
    String email;
    Date dateOfBirth;
    PatientAddress patientAddress;
    Set<Visit> visitSet;
}
