package pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.OnlineMedicalClinicUserEntity;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<OnlineMedicalClinicUserEntity, Integer> {
}
