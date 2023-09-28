package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import static org.assertj.core.api.Assertions.assertThat;



class AddVisitsMapperTest {

    @Test
    void thatMapFromDTOWorksCorrectly() {
//        given
        AddVisitsDTO addVisitsDTO = SomeFixtures.someAddVisitsDTO1();
        AddVisits addVisits = SomeFixtures.someAddVisits1();
//        when
        AddVisits target = AddVisitsMapper.INSTANCE.mapFromDTO(addVisitsDTO);
//        then
        assertThat(target).isEqualTo(addVisits);

    }
}