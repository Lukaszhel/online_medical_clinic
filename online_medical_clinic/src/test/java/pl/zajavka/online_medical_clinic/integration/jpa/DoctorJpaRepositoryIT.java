package pl.zajavka.online_medical_clinic.integration.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorAddressEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.DoctorAddressJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.zajavka.online_medical_clinic.integration.configuration.AbstractJpaIT;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.*;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class DoctorJpaRepositoryIT extends AbstractJpaIT {

    private DoctorJpaRepository doctorJpaRepository;
    @Test
    void thatDoctorCanBeSavedCorrectly(){
//        given
        String pwzDoctor2 = someDoctor2().getPwz();

        List<DoctorEntity> doctorEntities = List.of(someDoctorEntity1(), someDoctorEntity2());
        doctorJpaRepository.saveAllAndFlush(doctorEntities);
//        when
        List<DoctorEntity> resultAll = doctorJpaRepository.findAll();
        DoctorEntity resultDoctorByPwz = doctorJpaRepository.findByPwz(pwzDoctor2).get();
//        then
        assertThat(resultAll).hasSize(6);
        assertThat(resultDoctorByPwz.getPwz()).isEqualTo(pwzDoctor2);
    }

}