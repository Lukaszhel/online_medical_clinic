package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.DoctorDAO;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.exception.NotFoundException;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorDAO doctorDAO;

    @Test
    void thatFindAvailableWorksCorrectlyTest() {
//        given
        Mockito.when(doctorDAO.findAvailable()).thenReturn(
                List.of(SomeFixtures.someDoctor1(),
                        SomeFixtures.someDoctor2()));
//        when
        List<Doctor> result = doctorService.findAvailable();
//        then
        Assertions.assertEquals(2, result.size());

    }

    @ParameterizedTest
    @MethodSource
    void thatFindDoctorByPWZWorksCorrectlyTest(String pwz, Doctor expected) {
//        given
        Mockito.when(doctorDAO.findByPWZ(pwz)).thenReturn(Optional.of(expected));
//        when
        Doctor result = doctorService.findDoctorByPWZ(pwz);
//        then
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> thatFindDoctorByPWZWorksCorrectlyTest() {
        return Stream.of(
                Arguments.of("1234567", SomeFixtures.someDoctor1()),
                Arguments.of("7654321", SomeFixtures.someDoctor2())
        );
    }
    @Test
    void thatFindDoctorByPwzShouldThrowException(){
//        given
        Mockito.when(doctorDAO.findByPWZ(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
//        when
        Throwable exception = Assertions.assertThrows(NotFoundException.class, ()->doctorService.findDoctorByPWZ("1111111"));
//        then
        Assertions.assertEquals("Couldn't find doctor By his PWZ: [1111111]", exception.getMessage());

    }
}