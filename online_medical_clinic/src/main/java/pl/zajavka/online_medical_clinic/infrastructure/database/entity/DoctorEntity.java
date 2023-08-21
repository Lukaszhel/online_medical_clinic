package pl.zajavka.online_medical_clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "doctorId")
@ToString(of = {"doctorId", "name", "surname", "specialization", "pwz", "phone", "email"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

//    @Enumerated(EnumType.STRING)
    @Column(name = "specialization")
//    private Specialization specialization;
    private String specialization;

    @Column(name = "pwz", unique = true)
    private String pwz;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_address_id")
    private DoctorAddressEntity doctorAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorEntity")
    private Set<VisitEntity> visitEntitySet;

    /*public enum Specialization {
        INTERNIST,
        NEUROLOGIST,
        PEDIATRICIAN,
        EYEDOCTOR,
        UROLOGIST
    }*/
}
