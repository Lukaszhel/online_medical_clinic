package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.domain.Doctor;

@Mapper(componentModel = "spring")

public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);
    DoctorDTO mapToDTO(Doctor doctor);
//    Doctor mapFromDTO(DoctorDTO doctorDTO);

}
