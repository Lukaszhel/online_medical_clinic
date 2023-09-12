package pl.zajavka.online_medical_clinic.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.DoctorEntityMapper;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorRepositoryTest {

    @InjectMocks
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorJpaRepository doctorJpaRepository;

    @Mock
    private DoctorEntityMapper doctorEntityMapper;

    @Test
    void thatFindAvailableWorksCOrrectly() {
//        given
        when(doctorEntityMapper.mapFromEntity(any(DoctorEntity.class))).thenReturn(SomeFixtures.someDoctor1());
        when(doctorJpaRepository.findAll()).thenReturn(List.of(SomeFixtures.someDoctorEntity1()));
//        when
        List<Doctor> result = doctorRepository.findAvailable();
//        then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void thatFindByPWZWorksCorrectly() {
//        given
        String pwz = SomeFixtures.someDoctor1().getPwz();
        when(doctorEntityMapper.mapFromEntity(SomeFixtures.someDoctorEntity1())).thenReturn(SomeFixtures.someDoctor1());
        when(doctorJpaRepository.findByPwz(pwz)).thenReturn(Optional.of(SomeFixtures.someDoctorEntity1()));
//        when
        Optional<Doctor> result = doctorRepository.findByPWZ(pwz);
//        then
        Assertions.assertEquals(pwz, result.get().getPwz());
    }

}