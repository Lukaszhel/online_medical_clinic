package pl.zajavka.online_medical_clinic.integration.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.VisitEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.*;
import pl.zajavka.online_medical_clinic.integration.configuration.AbstractJpaIT;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.*;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class VisitJpaRepositoryIT extends AbstractJpaIT {

    private DoctorJpaRepository doctorJpaRepository;
    private VisitJpaRepository visitJpaRepository;
    private PatientJpaRepository patientJpaRepository;

    @Test
    void thatFindingVisitsWorksCorrectly() {
//        given
        DoctorEntity doctor1 = someDoctorEntity1();
        DoctorEntity doctor2 = someDoctorEntity2();
        PatientEntity patient1 = somePatientEntity1();
        PatientEntity patient2 = somePatientEntity2();
        Set<VisitEntity> visitEntities1 = Set.of(someVisitEntity1(), someVisitEntity1_1(), someVisitEntity1_2(), someVisitEntity1_3());
        Set<VisitEntity> visitEntities2 = Set.of(someVisitEntity2(), someVisitEntity2_1(), someVisitEntity2_2(), someVisitEntity2_3());

        String pwzDoctor1 = doctor1.getPwz();
        String peselPatient2 = patient2.getPesel();

        doctor1.setVisitEntitySet(visitEntities1);
        doctor2.setVisitEntitySet(visitEntities2);

        visitEntities1.forEach(visit-> visit.setDoctorEntity(doctor1));
        visitEntities2.forEach(visit-> visit.setDoctorEntity(doctor2));
        visitEntities1.forEach(visit-> visit.setPatientEntity(patient1));
        visitEntities2.forEach(visit-> visit.setPatientEntity(patient2));

        List<PatientEntity> patientEntities = List.of(patient1, patient2);
        patientJpaRepository.saveAll(patientEntities);

        List<DoctorEntity> doctorEntities = List.of(doctor1, doctor2);
        doctorJpaRepository.saveAllAndFlush(doctorEntities);

        //        when
        List<VisitEntity> resultAvailableVisitsForDoctorPwz = visitJpaRepository.findAvailableVisitsForDoctorPwz(pwzDoctor1);
        List<VisitEntity> resultAllVisitsByPatientPesel = visitJpaRepository.findAllByPatientPesel(peselPatient2);
        List<VisitEntity> resultAvailableVisitsByDoctorPwzWhereBookedIsfalse = visitJpaRepository.findAvailableVisitsByDoctorPwzWhereBookedIsfalse(pwzDoctor1);
        List<VisitEntity> all = visitJpaRepository.findAll();
//        then
        assertThat(resultAvailableVisitsForDoctorPwz).hasSize(4);
        assertThat(resultAllVisitsByPatientPesel).hasSize(4);
        assertThat(resultAvailableVisitsByDoctorPwzWhereBookedIsfalse).hasSize(0);
        assertThat(all).hasSize(8);

    }

}