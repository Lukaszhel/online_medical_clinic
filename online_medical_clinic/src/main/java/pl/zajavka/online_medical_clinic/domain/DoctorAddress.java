package pl.zajavka.online_medical_clinic.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "doctorAddressId")
@ToString(of = {"doctorAddressId", "country", "city", "postalCode", "street"})
public class DoctorAddress {
    Integer doctorAddressId;
    String country;
    String city;
    String postalCode;
    String street;
}
