package pl.zajavka.online_medical_clinic.business.dao;

import pl.zajavka.online_medical_clinic.domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {
    List<Patient> findAvailable();

    Optional<Patient> findPatientByPesel(String pesel);
}
