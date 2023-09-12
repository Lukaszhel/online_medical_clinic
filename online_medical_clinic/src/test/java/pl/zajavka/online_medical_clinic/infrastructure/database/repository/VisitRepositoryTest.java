package pl.zajavka.online_medical_clinic.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.VisitJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.DoctorEntityMapper;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.PatientEntityMapper;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.VisitEntityMapper;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitRepositoryTest {

    @InjectMocks
    private VisitRepository visitRepository;

    @Mock
    private VisitJpaRepository visitJpaRepository;

    @Mock
    private VisitEntityMapper visitEntityMapper;

    @Mock
    private DoctorEntityMapper doctorEntityMapper;

    @Mock
    private PatientEntityMapper patientEntityMapper;

    @Test
    void thatFindAvailableVisitsForDoctorPwzWorksCorrectly() {
//        given
        when(visitEntityMapper.mapFromEntity(SomeFixtures.someVisitEntity1())).thenReturn(SomeFixtures.someVisit1());
        when(visitJpaRepository.findAvailableVisitsForDoctorPwz(SomeFixtures.someDoctor1().getPwz())).thenReturn(List.of(SomeFixtures.someVisitEntity1()));
//        when
        List<Visit> result = visitRepository.findAvailableVisitsForDoctorPwz(SomeFixtures.someDoctor1().getPwz());
//        then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void thatFindAvailableVisitsForPatientByPeselWorksCorrectly() {
//    given
        when(visitJpaRepository.findAllByPatientPesel(SomeFixtures.somePatientEntity1().getPesel())).thenReturn(List.of(SomeFixtures.someVisitEntity1()));
        when(visitEntityMapper.mapFromEntity(SomeFixtures.someVisitEntity1())).thenReturn(SomeFixtures.someVisit1());
//    when
        List<Visit> result = visitRepository.findAvailableVisitsForPatientByPesel(SomeFixtures.somePatient1().getPesel());
//    then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void thatSaveVisitFromVisitsWorksCorrectly() {
//    given
        when(doctorEntityMapper.mapToEntity(SomeFixtures.someDoctor1())).thenReturn(SomeFixtures.someDoctorEntity1());
        when(visitEntityMapper.mapToEntity(SomeFixtures.someVisit1(), SomeFixtures.someDoctorEntity1())).thenReturn(SomeFixtures.someVisitEntity1());
//    when
        visitRepository.saveVisitFromVisits(SomeFixtures.someVisit1());
//    then
        verify(visitJpaRepository, times(1)).save(SomeFixtures.someVisitEntity1());
    }

    @Test
    void thatBookVisitForPatientWorksCorectly() {
//    given
        when(patientEntityMapper.mapToEntity(SomeFixtures.somePatient1())).thenReturn(SomeFixtures.somePatientEntity1());
//    when
        visitRepository.bookVisitForPatient(SomeFixtures.someVisit1().getVisitNumber(), SomeFixtures.somePatient1());
//    then
        verify(visitJpaRepository, times(1)).updateVisitByAddingPatientToVisit(SomeFixtures.someVisit1().getVisitNumber(), SomeFixtures.somePatientEntity1());
        verify(visitJpaRepository, times(1)).bookVisitForPatient(SomeFixtures.someVisit1().getVisitNumber());
    }

    @Test
    void thatWithdrawTheRegistrationForVisitWorksCorrectly() {
//    given
//    when
        visitRepository.withdrawTheRegistrationForVisit(SomeFixtures.someVisit1().getVisitNumber());
//    then
        verify(visitJpaRepository, times(1)).withdrawTheRegistrationForVisit(SomeFixtures.someVisit1().getVisitNumber());
    }

    @Test
    void thatAddCommentToVisitByVisitNumberWorksCorrectly() {
//    given
//    when
        visitRepository.addCommentToVisitByVisitNumber("anyString()", SomeFixtures.someVisit1().getVisitNumber());
//    then
        verify(visitJpaRepository, times(1)).addCommentToVisitByVisitNumber("anyString()", SomeFixtures.someVisit1().getVisitNumber());
    }


}