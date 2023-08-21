package pl.zajavka.online_medical_clinic.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.PatientDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PatientService {
    private final PatientDAO patientDAO;
    public List<Patient> findAvailable() {
        return patientDAO.findAvailable();
    }

    @Transactional
    public Patient findPatientByPesel(String pesel) {
        Optional<Patient> patient = patientDAO.findPatientByPesel(pesel);
        if (patient.isEmpty()) {
            throw new NotFoundException("Couldn't find patient By his pesel: [%s]".formatted(pesel));
        }
        return patient.get();
    }
}
