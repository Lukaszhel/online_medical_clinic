package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;

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
        Mockito.when(visitDAO.findAvailableVisitsForDoctorPwz("1234567")).thenReturn(List.of(SomeFixtures.someVisit1()));
//        when
        List<Visit> result = visitService.findAvailableVisitsForDoctorPwz("1234567");
//        then
        Assertions.assertEquals(1, result.size());
    }


    @Test
    void thatFindPatientByPeselWorksCorrectly() {
//        given
        Mockito.when(patientService.findPatientByPesel(SomeFixtures.somePatient1().getPesel())).thenReturn(SomeFixtures.somePatient1());
//        when
        Patient result = visitService.findPatientByPesel(SomeFixtures.somePatient1().getPesel());
//        then
        Assertions.assertEquals(SomeFixtures.somePatient1(), result);
    }

}