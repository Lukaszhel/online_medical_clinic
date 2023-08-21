package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.domain.Doctor;

@Mapper(componentModel = "spring")

public interface DoctorMapper {
    DoctorDTO mapToDTO(Doctor doctor);
//    Doctor mapFromDTO(DoctorDTO doctorDTO);

}
