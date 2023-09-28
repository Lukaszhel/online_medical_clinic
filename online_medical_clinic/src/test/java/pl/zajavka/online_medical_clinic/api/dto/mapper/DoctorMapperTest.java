package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import static org.assertj.core.api.Assertions.assertThat;
class DoctorMapperTest {

    @Test
    void thatMapToDTOWorksCorrectly() {
//        given
        DoctorDTO doctorDTO = SomeFixtures.someDoctorDTO1();
        Doctor doctor = SomeFixtures.someDoctor1();
//        when
        DoctorDTO target = DoctorMapper.INSTANCE.mapToDTO(doctor);
//        then
        assertThat(target).isEqualTo(doctorDTO);
    }
}