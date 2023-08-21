package pl.zajavka.online_medical_clinic.business.dao;

import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitDAO {

     List<Visit> findAvailableVisitsForDoctorPwz(String pwz);

    List<Visit> findAvailableVisitsForPatientByPesel(String pesel);

    void saveVisitFromVisits(Visit createdVisit);

    void updateVisitWithCommentByVisitNumber(String comment, String visitNumber);



    List<Visit> findAvailableVisitsByDoctorPwzWhereBookedIsfalse(String pwz);

    void updateBookVisitForPatient(String visitNumber, Patient patient);

    void withdrawTheRegistrationForVisit(String visitNumber);
}
