package pl.zajavka.online_medical_clinic.util;

import lombok.experimental.UtilityClass;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitsDTO;
import pl.zajavka.online_medical_clinic.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class SomeFixtures {

    public static Doctor someDoctor1(){
        return Doctor.builder()
                .doctorId(1)
                .name("Andrzej")
                .surname("Andrzejewski")
                .pwz("1234567")
                .email("and@gmail.com")
                .phone("+48 123 456 789")
                .specialization("UROLOGIST")
                .doctorAddress(DoctorAddress.builder()
                        .doctorAddressId(1)
                        .country("Poland")
                        .city("Warszawa")
                        .postalCode("87-100")
                        .street("Poznańska")
                        .build())
                .build();
    }

    public static Doctor someDoctor2(){
        return Doctor.builder()
                .doctorId(1)
                .name("Marek")
                .surname("Markowski")
                .pwz("7654321")
                .email("mar@gmail.com")
                .phone("+48 987 654 321")
                .specialization("INTERNIST")
                .doctorAddress(DoctorAddress.builder()
                        .doctorAddressId(2)
                        .country("Poland")
                        .city("Warszawa")
                        .postalCode("88-100")
                        .street("Krakowska")
                        .build())
                .build();
    }
    @SuppressWarnings("deprecation")
    public static Patient somePatient1(){
        return Patient.builder()
                .patientId(1)
                .name("Piotr")
                .surname("Piotrowski")
                .phone("+48 123 456 789")
                .pesel("12345678901")
                .email("piotr@gmail.com")
                .dateOfBirth(new Date(1960, Calendar.FEBRUARY, 1))
                .patientAddress(PatientAddress.builder()
                        .patientAddressId(1)
                        .country("Poland")
                        .city("Warszawa")
                        .postalCode("88-100")
                        .street("Krakowska")
                        .build())
                .build();
    }

    @SuppressWarnings("deprecation")
    public static Patient somePatient2(){
        return Patient.builder()
                .patientId(1)
                .name("Leszek")
                .surname("Leszkowski")
                .phone("+48 147 258 369")
                .pesel("98765432101")
                .email("lech@gmail.com")
                .dateOfBirth(new Date(1970, Calendar.FEBRUARY, 13))
                .patientAddress(PatientAddress.builder()
                        .patientAddressId(1)
                        .country("Poland")
                        .city("Warszawa")
                        .postalCode("88-100")
                        .street("Majowa")
                        .build())
                .build();
    }
    public static AddVisits someAddVisits() {
        return AddVisits.builder()
                .dateFrom(LocalDate.of(2024, 1, 1))
                .dateTo(LocalDate.of(2024, 1, 1))
                .timeFrom(LocalTime.of(10, 0, 0))
                .timeTo(LocalTime.of(10, 25, 0))
                .oneVisitTime(10)
                .doctorPWZ("1234567")
                .build();
    }

    public static Visit someVisit1(){
        return Visit.builder()
                .visitId(1)
                .doctor(someDoctor1())
                .patient(somePatient1())
                .visitNumber("135135-135-135-135-1")
                .dateTime(OffsetDateTime.of(LocalDate.of(2024,1 ,10), LocalTime.of(10, 0, 0, 0), ZoneOffset.UTC))
                .comment("some comment")
                .booked(true)
                .build();
    }

    public static DoctorDTO someDoctorDTO1(){
        return DoctorDTO.builder()
                .name(SomeFixtures.someDoctor1().getName())
                .surname(SomeFixtures.someDoctor1().getSurname())
                .pwz(SomeFixtures.someDoctor1().getPwz())
                .specialization(SomeFixtures.someDoctor1().getSpecialization())
                .build();
    }
    public static VisitDTO someVisitDTO1(){
        return VisitDTO.builder()
                .visitNumber(SomeFixtures.someVisit1().getVisitNumber())
                .dateTime(SomeFixtures.someVisit1().getDateTime())
                .doctorName(SomeFixtures.someDoctor1().getName())
                .doctorSurname(SomeFixtures.someDoctor1().getSurname())
                .patientPesel(SomeFixtures.somePatient1().getPesel())
                .doctorSpecialization(SomeFixtures.someDoctor1().getSpecialization())
                .booked(SomeFixtures.someVisit1().getBooked())
                .comment(SomeFixtures.someVisit1().getComment())
                .build();
    }
}
