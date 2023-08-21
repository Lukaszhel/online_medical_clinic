package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {

//    @Mapping(target = "visitSet", ignore = true)
    Doctor mapFromEntity(DoctorEntity doctorEntity);

    DoctorEntity mapToEntity(Doctor doctor);
}
