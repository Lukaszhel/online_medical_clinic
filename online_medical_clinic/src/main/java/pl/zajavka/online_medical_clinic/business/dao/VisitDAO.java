package pl.zajavka.online_medical_clinic.business.dao;

import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;

import java.util.List;

public interface VisitDAO {

     List<Visit> findAvailableVisitsForDoctorPwz(String pwz);

    List<Visit> findAvailableVisitsForPatientByPesel(String pesel);

    void saveVisitFromVisits(Visit createdVisit);

    void addCommentToVisitByVisitNumber(String comment, String visitNumber);



    List<Visit> findAvailableVisitsByDoctorPwzWhereBookedIsFalse(String pwz);

    void bookVisitForPatient(String visitNumber, Patient patient);

    void withdrawTheRegistrationForVisit(String visitNumber);
}
