package pl.zajavka.online_medical_clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDTO {
    private String visitNumber;
    private OffsetDateTime dateTime;
    private String patientPesel;
    private String doctorName;
    private String doctorSurname;
    private String doctorSpecialization;
    private Boolean booked;
    private String comment;

}
