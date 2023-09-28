package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.VisitEntity;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.*;

class VisitEntityMapperTest {

    @Test
    void thatMapFromEntityWorksCorrectly() {
//        given
        VisitEntity visitEntity = someVisitEntity1();
        Visit visit = someVisit1();
//        when
        Visit target = VisitEntityMapper.INSTANCE.mapFromEntity(visitEntity);
//        then
        assertThat(target).isEqualTo(visit);
    }

    @Test
    void thatMapToEntityWorksCorrectly() {
//        given
        VisitEntity visitEntity = someVisitEntity1();
        Visit visit = someVisit1();
        DoctorEntity doctorEntity = someDoctorEntity1();
//        when
        VisitEntity target = VisitEntityMapper.INSTANCE.mapToEntity(visit,doctorEntity);
//        then
        assertThat(target).isEqualTo(visitEntity);
    }
}