package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;

@Mapper(componentModel = "spring")
public interface AddVisitsMapper {
    AddVisits mapFromDTO(final AddVisitsDTO addVisitsDTO);
}
