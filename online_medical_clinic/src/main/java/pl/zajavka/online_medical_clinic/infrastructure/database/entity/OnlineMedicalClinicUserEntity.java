package pl.zajavka.online_medical_clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "userName", "email", "password", "active"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "online_medical_clinic_user")
public class OnlineMedicalClinicUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private DoctorEntity doctorEntity;


}
