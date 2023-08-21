package pl.zajavka.online_medical_clinic.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.business.dao.DoctorDAO;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.DoctorEntityMapper;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class DoctorRepository implements DoctorDAO {

    private final DoctorJpaRepository doctorJpaRepository;
    private final DoctorEntityMapper doctorEntityMapper;

    @Override
    public List<Doctor> findAvailable() {
        return doctorJpaRepository.findAll().stream()
                .map(doctorEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Doctor> findByPWZ(String doctorPWZ) {
        return doctorJpaRepository.findByPwz(doctorPWZ).
                map(doctorEntityMapper::mapFromEntity);
    }
}
