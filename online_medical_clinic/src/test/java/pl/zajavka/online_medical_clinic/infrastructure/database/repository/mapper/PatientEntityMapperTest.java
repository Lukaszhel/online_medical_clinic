package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.somePatient1;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.somePatientEntity1;

class PatientEntityMapperTest {

    @Test
    void thatMapFromEntityWorksCorrectly() {
//        given
        Patient patient = somePatient1();
        PatientEntity patientEntity = somePatientEntity1();
//        when
        Patient target = PatientEntityMapper.INSTANCE.mapFromEntity(patientEntity);
//        then
        assertThat(target).isEqualTo(patient);

    }

    @Test
    void thatMapToEntityWorksCorrectly() {
        //        given
        Patient patient = somePatient1();
        PatientEntity patientEntity = somePatientEntity1();
//        when
        PatientEntity target = PatientEntityMapper.INSTANCE.mapToEntity(patient);
//        then
        assertThat(target).isEqualTo(patientEntity);
    }
}