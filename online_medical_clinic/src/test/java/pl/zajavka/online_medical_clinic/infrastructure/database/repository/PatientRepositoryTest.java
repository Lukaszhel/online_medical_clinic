package pl.zajavka.online_medical_clinic.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.exception.NotFoundException;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.PatientEntityMapper;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientRepositoryTest {
    @InjectMocks
    private PatientRepository patientRepository;

    @Mock
    private PatientJpaRepository patientJpaRepository;

    @Mock
    private PatientEntityMapper patientEntityMapper;

    @Test
    void thatFindAvailableWorksCorrectly() {
//        given
        when(patientEntityMapper.mapFromEntity(SomeFixtures.somePatientEntity1())).thenReturn(SomeFixtures.somePatient1());
        when(patientJpaRepository.findAll()).thenReturn(List.of(SomeFixtures.somePatientEntity1()));
//        when
        List<Patient> result = patientRepository.findAvailable();
//        then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void
    thatFindPatientByPeselWorksCorrectly() {
//        given
        when(patientEntityMapper.mapFromEntity(SomeFixtures.somePatientEntity1())).thenReturn(SomeFixtures.somePatient1());
        when(patientJpaRepository.findByPesel(SomeFixtures.somePatient1().getPesel())).thenReturn(Optional.of(SomeFixtures.somePatientEntity1()));
//        when
        Optional<Patient> result = patientRepository.findPatientByPesel(SomeFixtures.somePatient1().getPesel());
//        then
        Assertions.assertEquals(SomeFixtures.somePatient1().getPesel(), result.orElseThrow(()-> new NotFoundException("Patient not found in test")).getPesel());
    }

}