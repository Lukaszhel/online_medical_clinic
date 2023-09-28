package pl.zajavka.online_medical_clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.Visit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitMapper {

    VisitMapper INSTANCE = Mappers.getMapper(VisitMapper.class);
    VisitDTO mapToDTO(final Visit visit);
//    @Mapping(target = "patientPesel", ignore = true)
    default VisitDTO mapToDTO(final Visit visit, final Doctor doctor){
        return VisitDTO.builder()
                .visitNumber(visit.getVisitNumber())
                .comment(visit.getComment())
                .dateTime(visit.getDateTime())
                .booked(visit.getBooked())
                .doctorName(doctor.getName())
                .doctorSurname(doctor.getSurname())
                .doctorSpecialization(doctor.getSpecialization())
                .build();
    }

}
