package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.domain.exception.ProcessingException;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddVisitsServiceTest {
    @Mock
    private DoctorService doctorService;
    @Mock
    private VisitDAO visitDAO;
    @InjectMocks
    private AddVisitsService addVisitsService;

    @ParameterizedTest
    @MethodSource
    void thatCreateVisitsDatesFromDoctorDataWorksCorrectly(AddVisits addVisits, int expected) {
//        given

//        when
        List<LocalDateTime> result = addVisitsService.createVisitsDatesFromDoctorData(addVisits);
//        then
        Assertions.assertEquals(expected, result.size());

    }

    public static Stream<Arguments> thatCreateVisitsDatesFromDoctorDataWorksCorrectly() {
        return Stream.of(
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 1))
                        .timeTo(LocalTime.of(14, 0))
                        .oneVisitTime(10)
                        .build(), 6),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 1))
                        .timeTo(LocalTime.of(13, 11))
                        .oneVisitTime(10)
                        .build(), 1),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 2))
                        .timeTo(LocalTime.of(13, 20))
                        .oneVisitTime(10)
                        .build(), 4),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 2))
                        .timeTo(LocalTime.of(13, 29))
                        .oneVisitTime(10)
                        .build(), 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    void thatCreateVisitsDatesFromDoctorDataThrowExceptionCorrectly(AddVisits addVisits) {
//        given
        Integer oneVisitTime = addVisits.getOneVisitTime();
        LocalDateTime dateTimeFrom = LocalDateTime.of(addVisits.getDateFrom(), addVisits.getTimeFrom());
        LocalDateTime dateTimeTo = LocalDateTime.of(addVisits.getDateTo(), addVisits.getTimeTo());

//        when
        ProcessingException exception = assertThrows(ProcessingException.class, () -> addVisitsService.createVisitsDatesFromDoctorData(addVisits));
//        then
        Assertions.assertEquals(String.format("Failed to create any visit. The time of one visit is: [%s]," +
                        " the entered data shows that the time between the start of patient admission: [%s] and " +
                        "its end: [%s] is too short to create at least one visit.",
                oneVisitTime, dateTimeFrom, dateTimeTo), exception.getMessage());

    }

    public static Stream<Arguments> thatCreateVisitsDatesFromDoctorDataThrowExceptionCorrectly() {
        return Stream.of(
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 1))
                        .timeTo(LocalTime.of(13, 0))
                        .oneVisitTime(10)
                        .build()),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 2))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 1))
                        .timeTo(LocalTime.of(13, 0))
                        .oneVisitTime(10)
                        .build()),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 1))
                        .timeTo(LocalTime.of(13, 9))
                        .oneVisitTime(10)
                        .build()),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 1))
                        .timeTo(LocalTime.of(12, 59))
                        .oneVisitTime(10)
                        .build()),
                Arguments.of(AddVisits.builder()
                        .dateFrom(LocalDate.of(2023, 2, 1))
                        .timeFrom(LocalTime.of(13, 0))
                        .dateTo(LocalDate.of(2023, 2, 2))
                        .timeTo(LocalTime.of(12, 59))
                        .oneVisitTime(10)
                        .build())
        );
    }

    @Test
    void thatCreateVisitsWorksCorrectly() {
//        given
        List<LocalDateTime> dateTimes = List.of(
                LocalDateTime.of(2023, 2, 1, 13, 0),
                LocalDateTime.of(2023, 2, 1, 13, 10));
//        when
        List<Visit> result = addVisitsService.createVisits(dateTimes, "1234567");
//        then
        Assertions.assertEquals(2, result.size());

    }

    @Test
    void thatSaveVisitsWorksCorrectly() {
//        given
        List<Visit> createdVisits = List.of(SomeFixtures.someVisit1(), SomeFixtures.someVisit2());

//        when
        addVisitsService.saveVisits(createdVisits);
//        then
        verify(visitDAO, times(2)).saveVisitFromVisits(any(Visit.class));

    }
}