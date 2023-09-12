package pl.zajavka.online_medical_clinic.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.VisitEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.VisitJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.DoctorEntityMapper;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.PatientEntityMapper;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.VisitEntityMapper;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class VisitRepository implements VisitDAO {

    private final VisitJpaRepository visitJpaRepository;
    private final VisitEntityMapper visitEntityMapper;
    private final DoctorEntityMapper doctorEntityMapper;
    private final PatientEntityMapper patientEntityMapper;

    @Override
    public List<Visit> findAvailableVisitsForDoctorPwz(String pwz) {
        return visitJpaRepository.findAvailableVisitsForDoctorPwz(pwz).stream()
                .map(visitEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Visit> findAvailableVisitsForPatientByPesel(String pesel) {
        return visitJpaRepository.findAllByPatientPesel(pesel).stream()
                .map(visitEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void saveVisitFromVisits(Visit createdVisit) {
        log.info("before saveVisit method with: [{}]", createdVisit);
        DoctorEntity doctorEntity = doctorEntityMapper.mapToEntity(createdVisit.getDoctor());
        VisitEntity visitEntity = visitEntityMapper.mapToEntity(createdVisit, doctorEntity);
        visitJpaRepository.save(visitEntity);
        log.info("after saveVisit method with: [{}]", visitEntity);
    }

    @Override
    public List<Visit> findAvailableVisitsByDoctorPwzWhereBookedIsFalse(String pwz) {
        return visitJpaRepository.findAvailableVisitsByDoctorPwzWhereBookedIsfalse(pwz)
                .stream().map(visitEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void bookVisitForPatient(String visitNumber, Patient patient) {
        PatientEntity patientEntity = patientEntityMapper.mapToEntity(patient);
        visitJpaRepository.updateVisitByAddingPatientToVisit(visitNumber, patientEntity);
        visitJpaRepository.bookVisitForPatient(visitNumber);
    }

    @Override
    public void withdrawTheRegistrationForVisit(String visitNumber) {
        visitJpaRepository.withdrawTheRegistrationForVisit(visitNumber);
    }

    @Override
    public void addCommentToVisitByVisitNumber(String comment, String visitNumber) {
        visitJpaRepository.addCommentToVisitByVisitNumber(comment, visitNumber);
    }
}