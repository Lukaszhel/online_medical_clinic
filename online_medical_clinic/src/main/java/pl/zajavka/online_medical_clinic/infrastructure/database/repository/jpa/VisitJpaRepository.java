package pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.VisitEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitJpaRepository extends JpaRepository<VisitEntity, Integer> {

/*    List<VisitEntity> findAllByDoctorEntity(DoctorEntity doctorEntity);

    Optional<VisitEntity> findByVisitNumber(String visitNumber);*/

    @Query("""
            SELECT visit FROM VisitEntity visit
            LEFT JOIN FETCH visit.doctorEntity doctor
            WHERE doctor.pwz = :pwz
            """)
    List<VisitEntity> findAvailableVisitsForDoctorPwz(@Param("pwz") String pwz);

    @Query("""
            SELECT visit FROM VisitEntity visit
            LEFT JOIN FETCH visit.patientEntity patient
            WHERE patient.pesel = :pesel
            """)
    List<VisitEntity> findAllByPatientPesel(@Param("pesel") String pesel);

    @Query("""
            UPDATE VisitEntity visit
            SET visit.comment = ?1
            WHERE visit.visitNumber IN (?2)
            """
    )
    @Modifying(clearAutomatically = true)
    void addCommentToVisitByVisitNumber(final String comment, final String visitNumber);


    @Query("""
            SELECT visit FROM VisitEntity visit
            LEFT JOIN FETCH visit.doctorEntity doctor
            WHERE doctor.pwz = :pwz and visit.booked = false
            """)
    List<VisitEntity> findAvailableVisitsByDoctorPwzWhereBookedIsfalse(@Param("pwz") String pwz);

    @Query("""
            UPDATE VisitEntity visit
            SET visit.patientEntity = :patient
            WHERE visit.visitNumber IN :visitNumber
            """
    )
    @Modifying(clearAutomatically = true)
    void updateVisitByAddingPatientToVisit(@Param("visitNumber")String visitNumber, @Param("patient")PatientEntity patient);

    @Query("""
            UPDATE VisitEntity visit
            SET visit.booked = TRUE
            WHERE visit.visitNumber IN :visitNumber
            """
    )
    @Modifying(clearAutomatically = true)
    void bookVisitForPatient(@Param("visitNumber")String visitNumber);

    @Query("""
            UPDATE VisitEntity visit
            SET visit.booked = FALSE
            WHERE visit.visitNumber IN :visitNumber
            """
    )
    @Modifying(clearAutomatically = true)
    void withdrawTheRegistrationForVisit(String visitNumber);
}