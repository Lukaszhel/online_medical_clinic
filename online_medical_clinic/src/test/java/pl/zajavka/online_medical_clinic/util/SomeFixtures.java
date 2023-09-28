package pl.zajavka.online_medical_clinic.util;

import lombok.experimental.UtilityClass;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.PeselPwzVisitNumberDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
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

    public static DoctorAddressEntity someDoctorAddressEntity1() {
        return DoctorAddressEntity.builder()
//                .doctorAddressId(8)
                .country("Poland")
                .city("Warszawa")
                .postalCode("87-100")
                .street("Poznańska")
                .build();
    }

    public static OnlineMedicalClinicUserEntity someUserDoctorEntity1() {
        return OnlineMedicalClinicUserEntity.builder()
                .userName("Andrew")
                .email("andrew@gmail.com")
                .password("password")
                .active(true)
                .build();
    }


    public static Doctor someDoctor1() {
        return Doctor.builder()
                .name("Andrzej")
                .surname("Andrzejewski")
                .pwz("1234567")
                .email("and@gmail.com")
                .phone("+48 123 456 789")
                .specialization("UROLOGIST")
                .doctorAddress(DoctorAddress.builder()
//                        .doctorAddressId(5)
                        .country(someDoctorAddressEntity1().getCountry())
                        .city(someDoctorAddressEntity1().getCity())
                        .postalCode(someDoctorAddressEntity1().getPostalCode())
                        .street(someDoctorAddressEntity1().getStreet())
                        .build())
                .build();
    }

    public static DoctorEntity someDoctorEntity1() {
        return DoctorEntity.builder()
                .name(someDoctor1().getName())
                .surname(someDoctor1().getSurname())
                .pwz(someDoctor1().getPwz())
                .email(someDoctor1().getEmail())
                .phone(someDoctor1().getPhone())
                .specialization(someDoctor1().getSpecialization())
                .doctorAddress(someDoctorAddressEntity1())
                .user(someUserDoctorEntity1())
                .build();
    }

    public static DoctorAddressEntity someDoctorAddressEntity2() {
        return DoctorAddressEntity.builder()
                .country("Poland")
                .city("Warszawa")
                .postalCode("88-100")
                .street("Krakowska")
                .build();
    }

    public static OnlineMedicalClinicUserEntity someUserDoctorEntity2() {
        return OnlineMedicalClinicUserEntity.builder()
                .userName("Mark")
                .email("mark@gmail.com")
                .password("password")
                .active(true)
                .build();
    }

    public static Doctor someDoctor2() {
        return Doctor.builder()
                .name("Marek")
                .surname("Markowski")
                .pwz("7654321")
                .email("mar@gmail.com")
                .phone("+48 987 654 321")
                .specialization("INTERNIST")
                .doctorAddress(DoctorAddress.builder()
//                        .doctorAddressId(6)
                        .country(someDoctorAddressEntity2().getCountry())
                        .city(someDoctorAddressEntity2().getCity())
                        .postalCode(someDoctorAddressEntity2().getPostalCode())
                        .street(someDoctorAddressEntity2().getStreet())
                        .build())
                .build();
    }

    public static DoctorEntity someDoctorEntity2() {
        return DoctorEntity.builder()
                .name(someDoctor2().getName())
                .surname(someDoctor2().getSurname())
                .pwz(someDoctor2().getPwz())
                .email(someDoctor2().getEmail())
                .phone(someDoctor2().getPhone())
                .specialization(someDoctor2().getSpecialization())
                .doctorAddress(someDoctorAddressEntity2())
                .user(someUserDoctorEntity2())
                .build();
    }

    public static PatientAddressEntity somePatientAddressEntity1() {
        return PatientAddressEntity.builder()
                .country("Poland")
                .city("Nekla")
                .postalCode("86-200")
                .street("Kotomierska")
                .build();
    }

    public static OnlineMedicalClinicUserEntity someUserPatientEntity1() {
        return OnlineMedicalClinicUserEntity.builder()
                .userName("Piter")
                .email("pit@gmail.com")
                .password("password")
                .active(true)
                .build();
    }

    @SuppressWarnings("deprecation")
    public static Patient somePatient1() {
        return Patient.builder()
                .name("Piotr")
                .surname("Piotrowski")
                .phone("+48 123 456 789")
                .pesel("12345678901")
                .email("piotr@gmail.com")
                .dateOfBirth(new Date(1960, Calendar.FEBRUARY, 1))
                .patientAddress(PatientAddress.builder()
                        .country(someDoctorAddressEntity1().getCountry())
                        .city(someDoctorAddressEntity1().getCity())
                        .postalCode(someDoctorAddressEntity1().getPostalCode())
                        .street(someDoctorAddressEntity1().getStreet())
                        .build())
                .build();
    }

    public static PatientEntity somePatientEntity1() {
        return PatientEntity.builder()
                .name(somePatient1().getName())
                .surname(somePatient1().getSurname())
                .phone(somePatient1().getPhone())
                .pesel(somePatient1().getPesel())
                .email(somePatient1().getEmail())
                .dateOfBirth(somePatient1().getDateOfBirth())
                .patientAddress(somePatientAddressEntity1())
                .user(someUserPatientEntity1())
                .build();
    }

    public static PatientAddressEntity somePatientAddressEntity2() {
        return PatientAddressEntity.builder()
                .country("Poland")
                .city("Nakło")
                .postalCode("86-300")
                .street("Szczecińska")
                .build();
    }

    public static OnlineMedicalClinicUserEntity someUserPatientEntity2() {
        return OnlineMedicalClinicUserEntity.builder()
                .userName("Lesiu")
                .email("lesiu@gmail.com")
                .password("password")
                .active(true)
                .build();
    }

    @SuppressWarnings("deprecation")
    public static Patient somePatient2() {
        return Patient.builder()
                .name("Leszek")
                .surname("Leszkowski")
                .phone("+48 147 258 369")
                .pesel("98765432101")
                .email("lech@gmail.com")
                .dateOfBirth(new Date(1970, Calendar.FEBRUARY, 13))
                .patientAddress(PatientAddress.builder()
                        .country(somePatientAddressEntity2().getCountry())
                        .city(somePatientAddressEntity2().getCity())
                        .postalCode(somePatientAddressEntity2().getPostalCode())
                        .street(somePatientAddressEntity2().getStreet())
                        .build())
                .build();
    }

    public static PatientEntity somePatientEntity2() {
        return PatientEntity.builder()
                .name(somePatient2().getName())
                .surname(somePatient2().getSurname())
                .phone(somePatient2().getPhone())
                .pesel(somePatient2().getPesel())
                .email(somePatient2().getEmail())
                .dateOfBirth(somePatient2().getDateOfBirth())
                .patientAddress(somePatientAddressEntity2())
                .user(someUserPatientEntity2())
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

    public static Visit someVisit1() {
        return Visit.builder()
//                .visitId(1)
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
                                someAddVisits1().getTimeFrom().getMinute()),
                        ZoneOffset.UTC))
                .comment("some comment")
                .booked(true)
                .build();
    }

    public static VisitEntity someVisitEntity1() {
        return VisitEntity.builder()
//                .visitId(someVisit1().getVisitId())
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .visitNumber(someVisit1().getVisitNumber())
                .dateTime(someVisit1().getDateTime())
                .comment(someVisit1().getComment())
                .booked(someVisit1().getBooked())
                /* .doctorEntity(someDoctorEntity1())
                 .patientEntity(somePatientEntity1())*/
                .build();
    }

    public static VisitEntity someVisitEntity1WithDoctor1() {
        return VisitEntity.builder()
//                .visitId(someVisit1().getVisitId())
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .visitNumber(someVisit1().getVisitNumber())
                .dateTime(someVisit1().getDateTime())
                .comment(someVisit1().getComment())
                .booked(someVisit1().getBooked())
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .build();
    }

    public static VisitEntity someVisitEntity1_1() {
        return VisitEntity.builder()
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .visitNumber("135135-135-135-135-1-1")
                .dateTime(someVisit1().getDateTime().plusMinutes(someAddVisits1().getOneVisitTime()))
                .comment(someVisit1().getComment())
                .booked(someVisit1().getBooked())
//                .doctorEntity(someDoctorEntity1())
//                .patientEntity(somePatientEntity1())
                .build();
    }

    public static VisitEntity someVisitEntity1_2() {
        return VisitEntity.builder()
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .visitNumber("135135-135-135-135-1-2")
                .dateTime(someVisitEntity1_1().getDateTime().plusMinutes(someAddVisits1().getOneVisitTime()))
                .comment(someVisit1().getComment())
                .booked(someVisit1().getBooked())
//                .doctorEntity(someDoctorEntity1())
//                .patientEntity(somePatientEntity1())
                .build();
    }

    public static VisitEntity someVisitEntity1_3() {
        return VisitEntity.builder()
                .doctorEntity(someDoctorEntity1())
                .patientEntity(somePatientEntity1())
                .visitNumber("135135-135-135-135-1-3")
                .dateTime(someVisitEntity1_2().getDateTime().plusMinutes(someAddVisits1().getOneVisitTime()))
                .comment(someVisit1().getComment())
                .booked(someVisit1().getBooked())
//                .doctorEntity(someDoctorEntity1())
//                .patientEntity(somePatientEntity1())
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

    public static Visit someVisit2() {
        return Visit.builder()
//                .visitId(2)
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
                                someAddVisits2().getTimeFrom().getMinute()),
                        ZoneOffset.UTC))
                .comment("some comment")
                .booked(true)
                .build();
    }

    public static VisitEntity someVisitEntity2() {
        return VisitEntity.builder()
//                .visitId(someVisit2().getVisitId())
                .doctorEntity(someDoctorEntity2())
                .patientEntity(somePatientEntity2())
                .visitNumber(someVisit2().getVisitNumber())
                .dateTime(someVisit2().getDateTime())
                .comment(someVisit2().getComment())
                .booked(someVisit2().getBooked())
//                .doctorEntity(someDoctorEntity2())
//                .patientEntity(somePatientEntity2())
                .build();
    }

    public static VisitEntity someVisitEntity2_1() {
        return VisitEntity.builder()
                .doctorEntity(someDoctorEntity2())
                .patientEntity(somePatientEntity2())
                .visitNumber("235235-235-235-235-2-1")
                .dateTime(someVisit2().getDateTime().plusMinutes(someAddVisits2().getOneVisitTime()))
                .comment(someVisit2().getComment())
                .booked(someVisit2().getBooked())
//                .doctorEntity(someDoctorEntity2())
//                .patientEntity(somePatientEntity2())
                .build();
    }

    public static VisitEntity someVisitEntity2_2() {
        return VisitEntity.builder()
                .doctorEntity(someDoctorEntity2())
                .patientEntity(somePatientEntity2())
                .visitNumber("235235-235-235-235-2-2")
                .dateTime(someVisitEntity2_1().getDateTime().plusMinutes(someAddVisits2().getOneVisitTime()))
                .comment(someVisit2().getComment())
                .booked(someVisit2().getBooked())
//                .doctorEntity(someDoctorEntity2())
//                .patientEntity(somePatientEntity2())
                .build();
    }

    public static VisitEntity someVisitEntity2_3() {
        return VisitEntity.builder()
                .doctorEntity(someDoctorEntity2())
                .patientEntity(somePatientEntity2())
                .visitNumber("235235-235-235-235-2-3")
                .dateTime(someVisitEntity2_2().getDateTime().plusMinutes(someAddVisits2().getOneVisitTime()))
                .comment(someVisit2().getComment())
                .booked(someVisit2().getBooked())
//                .doctorEntity(someDoctorEntity2())
//                .patientEntity(somePatientEntity2())
                .build();
    }

    public static DoctorDTO someDoctorDTO1() {
        return DoctorDTO.builder()
                .name(someDoctor1().getName())
                .surname(someDoctor1().getSurname())
                .pwz(someDoctor1().getPwz())
                .specialization(someDoctor1().getSpecialization())
                .build();
    }

    public static VisitDTO someVisitDTO1() {
        return VisitDTO.builder()
                .visitNumber(someVisit1().getVisitNumber())
                .dateTime(someVisit1().getDateTime())
                .booked(someVisit1().getBooked())
                .comment(someVisit1().getComment())
                .build();
    }

    public static VisitDTO someVisitDTO1WithDoctor1() {
        return VisitDTO.builder()
                .visitNumber(someVisit1().getVisitNumber())
                .dateTime(someVisit1().getDateTime())
                .booked(someVisit1().getBooked())
                .comment(someVisit1().getComment())
                .doctorName(someDoctor1().getName())
                .doctorSurname(someDoctor1().getSurname())
                .doctorSpecialization(someDoctor1().getSpecialization())
                .build();
    }

    public static AddVisitsDTO someAddVisitsDTO1() {
        return AddVisitsDTO.builder()
                .doctorPWZ(someDoctor1().getPwz())
                .oneVisitTime(someAddVisits1().getOneVisitTime())
                .dateFrom(someAddVisits1().getDateFrom())
                .dateTo(someAddVisits1().getDateTo())
                .timeFrom(someAddVisits1().getTimeFrom())
                .timeTo(someAddVisits1().getTimeTo())
                .build();
    }

    public static PeselPwzVisitNumberDTO somePeselPwzVisitNumberDTO() {
        return PeselPwzVisitNumberDTO.builder()
                .pesel(somePatient1().getPesel())
                .pwz(someDoctor1().getPwz())
                .visitNumber(someVisit1().getVisitNumber())
                .build();
    }

}
