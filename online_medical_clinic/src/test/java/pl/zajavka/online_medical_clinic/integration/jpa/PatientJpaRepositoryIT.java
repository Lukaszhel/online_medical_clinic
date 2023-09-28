package pl.zajavka.online_medical_clinic.integration.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientAddressEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.PatientAddressJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.zajavka.online_medical_clinic.integration.configuration.AbstractJpaIT;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.zajavka.online_medical_clinic.util.SomeFixtures.*;

@AllArgsConstructor(onConstructor = @__(@Autowired))
class PatientJpaRepositoryIT extends AbstractJpaIT {

    private PatientJpaRepository patientJpaRepository;
    @Test
    void thatPatientCanBeSavedCorrectly(){
//        given
        String peselPatient2 = somePatient2().getPesel();

        List<PatientEntity> patientEntities = List.of(somePatientEntity1(), somePatientEntity2());
        patientJpaRepository.saveAllAndFlush(patientEntities);

//        when
        List<PatientEntity> resultAll = patientJpaRepository.findAll();
        PatientEntity resultPatientByPesel = patientJpaRepository.findByPesel(peselPatient2).get();

//        then
        assertThat(resultAll).hasSize(5);
        assertThat(resultPatientByPesel.getPesel()).isEqualTo(peselPatient2);
    }

}