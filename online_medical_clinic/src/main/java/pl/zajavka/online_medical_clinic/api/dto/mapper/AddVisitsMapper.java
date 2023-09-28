package pl.zajavka.online_medical_clinic.api.dto.mapper;

import lombok.Value;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;

import javax.annotation.processing.Generated;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.CLASS;


@Mapper(componentModel = "spring")
public interface AddVisitsMapper {

    AddVisitsMapper INSTANCE = Mappers.getMapper(AddVisitsMapper.class);
    AddVisits mapFromDTO(final AddVisitsDTO addVisitsDTO);
}
