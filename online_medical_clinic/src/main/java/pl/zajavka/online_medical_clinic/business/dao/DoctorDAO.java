package pl.zajavka.online_medical_clinic.business.dao;

import pl.zajavka.online_medical_clinic.domain.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorDAO {
    List<Doctor> findAvailable();

    Optional<Doctor> findByPWZ(String doctorPWZ);

}
