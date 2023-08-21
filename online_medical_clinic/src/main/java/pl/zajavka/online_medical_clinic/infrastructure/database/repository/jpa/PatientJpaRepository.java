package pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;

import java.util.Optional;

@Repository
public interface PatientJpaRepository extends JpaRepository<PatientEntity, Integer> {
    Optional<PatientEntity> findByPesel(String pesel);
}
