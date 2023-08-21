package pl.zajavka.online_medical_clinic.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.DoctorDAO;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorDAO doctorDAO;

    @Transactional
    public List<Doctor> findAvailable() {
        List<Doctor> availableDoctors = doctorDAO.findAvailable();
        log.info("Available Doctor: [{}]", availableDoctors.size());
        return availableDoctors;
    }

    @Transactional
    public Doctor findDoctorByPWZ(String doctorPWZ) {
        Optional<Doctor> doctor = doctorDAO.findByPWZ(doctorPWZ);
        if (doctor.isEmpty()) {
            throw new NotFoundException("Couldn't find doctor By his PWZ: [%s]".formatted(doctorPWZ));
        }
        return doctor.get();
    }


}
