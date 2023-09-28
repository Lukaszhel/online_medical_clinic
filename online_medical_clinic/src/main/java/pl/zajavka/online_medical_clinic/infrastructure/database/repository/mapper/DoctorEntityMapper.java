package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {

    DoctorEntityMapper INSTANCE = Mappers.getMapper(DoctorEntityMapper.class);

//    @Mapping(target = "visitSet", ignore = true)
    Doctor mapFromEntity(DoctorEntity doctorEntity);

    DoctorEntity mapToEntity(Doctor doctor);
}
