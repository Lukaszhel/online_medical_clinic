package pl.zajavka.online_medical_clinic.util;

import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.language.bm.Languages;
import lombok.experimental.UtilityClass;
import pl.zajavka.online_medical_clinic.api.dto.*;
import pl.zajavka.online_medical_clinic.domain.*;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

@UtilityClass
public class SomeFixtures {

    public static Doctor someDoctor1() {
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
    public static DoctorEntity someDoctorEntity1() {
        return DoctorEntity.builder()
                .doctorId(1)
                .name(SomeFixtures.someDoctor1().getName())
                .surname(SomeFixtures.someDoctor1().getSurname())
                .pwz(SomeFixtures.someDoctor1().getPwz())
                .email(SomeFixtures.someDoctor1().getEmail())
                .phone(SomeFixtures.someDoctor1().getPhone())
                .specialization(SomeFixtures.someDoctor1().getSpecialization())
                .doctorAddress(DoctorAddressEntity.builder()
                        .doctorAddressId(SomeFixtures.someDoctor1().getDoctorAddress().getDoctorAddressId())
                        .country(SomeFixtures.someDoctor1().getDoctorAddress().getCountry())
                        .city(SomeFixtures.someDoctor1().getDoctorAddress().getCity())
                        .postalCode(SomeFixtures.someDoctor1().getDoctorAddress().getPostalCode())
                        .street(SomeFixtures.someDoctor1().getDoctorAddress().getStreet())
                        .build())
                .build();
    }

    public static Doctor someDoctor2() {
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
    public static Patient somePatient1() {
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
    public static PatientEntity somePatientEntity1() {
        return PatientEntity.builder()
                .patientId(SomeFixtures.somePatient1().getPatientId())
                .name(SomeFixtures.somePatient1().getName())
                .surname(SomeFixtures.somePatient1().getSurname())
                .phone(SomeFixtures.somePatient1().getPhone())
                .pesel(SomeFixtures.somePatient1().getPesel())
                .email(SomeFixtures.somePatient1().getEmail())
                .dateOfBirth(SomeFixtures.somePatient1().getDateOfBirth())
                .patientAddress(PatientAddressEntity.builder()
                        .patientAddressId(SomeFixtures.somePatient1().getPatientAddress().getPatientAddressId())
                        .country(SomeFixtures.somePatient1().getPatientAddress().getCountry())
                        .city(SomeFixtures.somePatient1().getPatientAddress().getCity())
                        .postalCode(SomeFixtures.somePatient1().getPatientAddress().getPostalCode())
                        .street(SomeFixtures.somePatient1().getPatientAddress().getStreet())
                        .build())
                .build();
    }

    @SuppressWarnings("deprecation")
    public static Patient somePatient2() {
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

    public static AddVisits someAddVisits1() {
        return AddVisits.builder()
                .dateFrom(LocalDate.of(2023, 10, 10))
                .dateTo(LocalDate.of(2023, 10, 11))
                .timeFrom(LocalTime.of(10, 0, 0))
                .timeTo(LocalTime.of(11, 0, 0))
                .oneVisitTime(10)
                .doctorPWZ(someDoctor1().getPwz())
                .build();
    }
    public static AddVisits someAddVisits2() {
        return AddVisits.builder()
                .dateFrom(LocalDate.of(2023, 10, 12))
                .dateTo(LocalDate.of(2023, 10, 12))
                .timeFrom(LocalTime.of(10, 0, 0))
                .timeTo(LocalTime.of(11, 0, 0))
                .oneVisitTime(10)
                .doctorPWZ(someDoctor2().getPwz())
                .build();
    }

    public static Visit someVisit1() {
        return Visit.builder()
                .visitId(1)
                .doctor(someDoctor1())
                .patient(somePatient1())
                .visitNumber("135135-135-135-135-1")
                .dateTime(OffsetDateTime.of(
                        LocalDate.of(
                                someAddVisits1().getDateFrom().getYear(),
                                someAddVisits1().getDateFrom().getMonth(),
                                someAddVisits1().getDateFrom().getDayOfMonth()),
                        LocalTime.of(
                                someAddVisits1().getTimeFrom().getHour(),
                                someAddVisits1().getTimeFrom().getMinute(),
                                0,
                                0),
                        ZoneOffset.UTC))
                .comment("some comment")
                .booked(true)
                .build();
    }
    public static VisitEntity someVisitEntity1(){
        return VisitEntity.builder()
                .visitId(someVisit1().getVisitId())
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .visitNumber(someVisit1().getVisitNumber())
                .dateTime(someVisit1().getDateTime())
                .comment(someVisit1().getComment())
                .booked(someVisit1().getBooked())
                .build();
    }
    public static Visit someVisit2() {
        return Visit.builder()
                .visitId(2)
                .doctor(someDoctor2())
                .patient(somePatient2())
                .visitNumber("235235-235-235-235-2")
                .dateTime(OffsetDateTime.of(
                        LocalDate.of(
                                someAddVisits2().getDateFrom().getYear(),
                                someAddVisits2().getDateFrom().getMonth(),
                                someAddVisits2().getDateFrom().getDayOfMonth()),
                        LocalTime.of(
                                someAddVisits2().getTimeFrom().getHour(),
                                someAddVisits2().getTimeFrom().getMinute(),
                                0,
                                0),
                        ZoneOffset.UTC))
                .comment("some comment")
                .booked(true)
                .build();
    }

    public static DoctorDTO someDoctorDTO1() {
        return DoctorDTO.builder()
                .name(SomeFixtures.someDoctor1().getName())
                .surname(SomeFixtures.someDoctor1().getSurname())
                .pwz(SomeFixtures.someDoctor1().getPwz())
                .specialization(SomeFixtures.someDoctor1().getSpecialization())
                .build();
    }

    public static VisitDTO someVisitDTO1() {
        return VisitDTO.builder()
                .visitNumber(SomeFixtures.someVisit1().getVisitNumber())
                .dateTime(SomeFixtures.someVisit1().getDateTime())
                .booked(SomeFixtures.someVisit1().getBooked())
                .comment(SomeFixtures.someVisit1().getComment())
                .build();
    }
    public static AddVisitsDTO someAddVisitsDTO1(){
        return AddVisitsDTO.builder()
                .doctorPWZ(someDoctor1().getPwz())
                .oneVisitTime(someAddVisits1().getOneVisitTime())
                .dateFrom(someAddVisits1().getDateFrom())
                .dateTo(someAddVisits1().getDateTo())
                .timeFrom(someAddVisits1().getTimeFrom())
                .timeTo(someAddVisits1().getTimeTo())
                .build();
    }

    public static PeselPwzVisitNumberDTO somePeselPwzVisitNumberDTO(){
        return PeselPwzVisitNumberDTO.builder()
                .pesel(SomeFixtures.somePatient1().getPesel())
                .pwz(SomeFixtures.someDoctor1().getPwz())
                .visitNumber(SomeFixtures.someVisit1().getVisitNumber())
                .build();
    }

}
