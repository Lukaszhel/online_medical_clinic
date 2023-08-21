package pl.zajavka.online_medical_clinic.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.business.dao.PatientDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.PatientEntityMapper;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class PatientRepository implements PatientDAO {

    private final PatientJpaRepository patientJpaRepository;
    private final PatientEntityMapper patientEntityMapper;
    @Override
    public List<Patient> findAvailable() {
        return patientJpaRepository.findAll().stream().map(patientEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Patient> findPatientByPesel(String pesel) {
       return patientJpaRepository.findByPesel(pesel).map(patientEntityMapper::mapFromEntity);
    }
}
