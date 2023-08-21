package pl.zajavka.online_medical_clinic.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "visitId")
@ToString(of = {"visitId", "visitNumber", "dateTime", "comment", "booked"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visit")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer visitId;

    @Column(name = "visit_number", unique = true)
    private String visitNumber;

    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "booked")
    private Boolean booked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patientEntity;


    public enum Completed {
        WAIT,
        COMPLETED,
        MISSING
    }

}
