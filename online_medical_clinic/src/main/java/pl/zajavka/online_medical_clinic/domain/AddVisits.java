package pl.zajavka.online_medical_clinic.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.time.LocalTime;

@With
@Value
@Builder
public class AddVisits {
    LocalDate dateFrom;
    LocalDate dateTo;
    LocalTime timeFrom;
    LocalTime timeTo;
    Integer oneVisitTime;
    String doctorPWZ;
}
