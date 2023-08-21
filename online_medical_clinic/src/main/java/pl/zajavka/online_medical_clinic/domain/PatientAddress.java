package pl.zajavka.online_medical_clinic.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "patientAddressId")
@ToString(of = {"patientAddressId", "country", "city", "postalCode", "street"})
public class PatientAddress {
    Integer patientAddressId;
    String country;
    String city;
    String postalCode;
    String street;
}
