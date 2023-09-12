package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceTest {

    @InjectMocks
    private VisitService visitService;

    @Mock
    private VisitDAO visitDAO;

    @Mock
    private PatientService patientService;

    @Test
    void thatFindAvailableVisitsForDoctorPwzWorksCorrectly() {
//        given
        when(visitDAO.findAvailableVisitsForDoctorPwz("1234567")).thenReturn(List.of(SomeFixtures.someVisit1()));
//        when
        List<Visit> result = visitService.findAvailableVisitsForDoctorPwz("1234567");
//        then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void thatAddCommentToVisitByVisitNumberWorksCorrectly() {
//        given, when
        visitService.addCommentToVisitByVisitNumber(anyString(), anyString());
//        then
        verify(visitDAO, times(1)).addCommentToVisitByVisitNumber(anyString(), anyString());

    }

    @Test
    void thatFindAvailableVisitsForPatientByPeselWorksCorrectly() {
//        given
        when(visitDAO.findAvailableVisitsForPatientByPesel(anyString())).thenReturn(List.of(SomeFixtures.someVisit1(), SomeFixtures.someVisit2()));
//        when
        List<Visit> result = visitService.findAvailableVisitsForPatientByPesel(anyString());
//        then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void thatFindAvailableVisitsByDoctorPwzWhereBookedIsFalseWorksCorrectly() {
//        given
        when(visitDAO.findAvailableVisitsByDoctorPwzWhereBookedIsFalse(anyString())).thenReturn(List.of(SomeFixtures.someVisit1().withBooked(false)));
//        when
        List<Visit> result = visitService.findAvailableVisitsByDoctorPwzWhereBookedIsFalse(anyString());
//        then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void thatBookVisitForPatientWorksCorrectly(){
//        given, when
        visitService.bookVisitForPatient("anyString()", SomeFixtures.somePatient1());
//        then
        verify(visitDAO, times(1)).bookVisitForPatient("anyString()", SomeFixtures.somePatient1());
    }

    @Test
    void thatFindPatientByPeselWorksCorrectly() {
//        given
        when(patientService.findPatientByPesel(SomeFixtures.somePatient1().getPesel())).thenReturn(SomeFixtures.somePatient1());
//        when
        Patient result = visitService.findPatientByPesel(SomeFixtures.somePatient1().getPesel());
//        then
        Assertions.assertEquals(SomeFixtures.somePatient1(), result);
    }
    @Test
    void withdrawTheRegistrationForVisitWorksCorrectly(){
//        given, when
        visitService.withdrawTheRegistrationForVisit(anyString());
//        then
        verify(visitDAO, times(1)).withdrawTheRegistrationForVisit(anyString());

    }

}