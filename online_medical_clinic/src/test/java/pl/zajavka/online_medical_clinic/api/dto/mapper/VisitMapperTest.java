package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import static org.assertj.core.api.Assertions.assertThat;
class VisitMapperTest {

    @Test
    void thatMapToDTOWorksCorrectly() {
//        given
        Visit visit = SomeFixtures.someVisit1();
        VisitDTO visitDTO = SomeFixtures.someVisitDTO1();
//        when
        VisitDTO target = VisitMapper.INSTANCE.mapToDTO(visit);
//        then
        assertThat(target).isEqualTo(visitDTO);
    }

    @Test
    void testMapToDTO() {
//        given
        Visit visit = SomeFixtures.someVisit1();
        VisitDTO visitDTO = SomeFixtures.someVisitDTO1WithDoctor1();
        Doctor doctor = SomeFixtures.someDoctor1();
//        when
        VisitDTO target = VisitMapper.INSTANCE.mapToDTO(visit, doctor);
//        then
        assertThat(target).isEqualTo(visitDTO);

    }
}