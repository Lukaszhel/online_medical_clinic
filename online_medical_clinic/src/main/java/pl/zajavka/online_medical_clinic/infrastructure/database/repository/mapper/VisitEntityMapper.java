package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.DoctorEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.VisitEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitEntityMapper {

    VisitEntityMapper INSTANCE = Mappers.getMapper(VisitEntityMapper.class);

//    VisitEntity mapToEntity(Visit visit);

    Visit mapFromEntity(VisitEntity visitEntity);


    VisitEntity mapToEntity(Visit visit, DoctorEntity doctorEntity);




}
