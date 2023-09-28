package pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorAddressEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientAddressEntity;

@Repository
public interface PatientAddressJpaRepository extends JpaRepository<PatientAddressEntity, Integer> {
}
