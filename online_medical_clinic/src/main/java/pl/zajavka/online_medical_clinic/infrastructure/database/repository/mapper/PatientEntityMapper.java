package pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.PatientEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PatientEntityMapper {
    Patient mapFromEntity(PatientEntity patientEntity);
    PatientEntity mapToEntity(Patient patient);
}
