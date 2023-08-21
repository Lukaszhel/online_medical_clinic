package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.PatientDAO;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.exception.NotFoundException;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientDAO patientDAO;


    @Test
    void thatFindAvailableWorksCorrectly() {
//        given
        Mockito.when(patientDAO.findAvailable()).thenReturn(List.of(SomeFixtures.somePatient1(), SomeFixtures.somePatient2()));
//        when
        List<Patient> result = patientService.findAvailable();
//        then
        Assertions.assertEquals(2, result.size());
    }

    @ParameterizedTest
    @MethodSource
    void thatFindPatientByPeselWorksCorrectly(String pesel, Patient expected) {
//        given
        Mockito.when(patientDAO.findPatientByPesel(pesel)).thenReturn(Optional.of(expected));
//        when
        Patient result = patientService.findPatientByPesel(pesel);
//        then
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> thatFindPatientByPeselWorksCorrectly() {
        return Stream.of(
                Arguments.of("12345678901", SomeFixtures.somePatient1()),
                Arguments.of("98765432101", SomeFixtures.somePatient2())
        );
    }
    @Test
    void thatFindPatientByPeselThrowsExceptionCorrectly(){
//        given
        Mockito.when(patientDAO.findPatientByPesel(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
//        when
        Throwable exception = Assertions.assertThrows(NotFoundException.class, ()->patientService.findPatientByPesel("11111111111"));
//        then
        Assertions.assertEquals("Couldn't find patient By his pesel: [11111111111]", exception.getMessage());

    }
}