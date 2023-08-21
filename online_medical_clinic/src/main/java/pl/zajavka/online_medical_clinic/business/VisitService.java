package pl.zajavka.online_medical_clinic.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VisitService {

    private final VisitDAO visitDAO;
    private final PatientService patientService;

    @Transactional
    public List<Visit> findAvailableVisitsForDoctorPwz(String pwz) {
        return visitDAO.findAvailableVisitsForDoctorPwz(pwz);
    }

    @Transactional
    public void updateVisitWithCommentByVisitNumber(String comment, String visitNumber) {
        visitDAO.updateVisitWithCommentByVisitNumber(comment, visitNumber);
    }

    @Transactional
    public List<Visit> findAvailableVisitsForPatientByPesel(String pesel) {
        return visitDAO.findAvailableVisitsForPatientByPesel(pesel);
    }

    @Transactional
    public List<Visit> findAvailableVisitsByDoctorPwzWhereBookedIsfalse(String pwz) {
        return visitDAO.findAvailableVisitsByDoctorPwzWhereBookedIsfalse(pwz);
    }

    @Transactional
    public void updateBookVisitForPatient(String visitNumber, Patient patientId) {
        visitDAO.updateBookVisitForPatient(visitNumber, patientId);
    }

    @Transactional
    public Patient findPatientByPesel(String pesel) {
        return patientService.findPatientByPesel(pesel);
    }

    @Transactional
    public void withdrawTheRegistrationForVisit(String visitNumber) {
        visitDAO.withdrawTheRegistrationForVisit(visitNumber);
    }
}
