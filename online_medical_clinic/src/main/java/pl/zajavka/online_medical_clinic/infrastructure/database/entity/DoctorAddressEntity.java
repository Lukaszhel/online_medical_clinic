package pl.zajavka.online_medical_clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "doctorAddressId")
@ToString(of = {"doctorAddressId","country", "city", "postalCode", "street"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_address")
public class DoctorAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_address_id")
    private Integer doctorAddressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "doctorAddress")
    private DoctorEntity doctorEntity;


}
