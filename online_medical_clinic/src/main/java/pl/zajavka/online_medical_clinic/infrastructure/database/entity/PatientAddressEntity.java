package pl.zajavka.online_medical_clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "patientAddressId")
@ToString(of = {"patientAddressId","country", "city", "postalCode", "street"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_address")
public class PatientAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_address_id")
    private Integer patientAddressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;


}
