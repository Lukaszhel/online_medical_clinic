package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.*;

class DoctorEntityMapperTest {

    @Test
    void thatMapFromEntityWorksCorrectly() {
//        given
        Doctor doctor = someDoctor1();
        DoctorEntity doctorEntity = someDoctorEntity1();
//        when
        Doctor target = DoctorEntityMapper.INSTANCE.mapFromEntity(doctorEntity);
//        then
        assertThat(target).isEqualTo(doctor);

    }

    @Test
    void thatMapToEntityWorksCorrectly() {
//        given
        Doctor doctor = someDoctor1();
        DoctorEntity doctorEntity = someDoctorEntity1();
//        when
        DoctorEntity target = DoctorEntityMapper.INSTANCE.mapToEntity(doctor);
//        then
        assertThat(target).isEqualTo(doctorEntity);
    }
}