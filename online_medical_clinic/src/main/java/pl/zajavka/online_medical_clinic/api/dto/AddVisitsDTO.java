package pl.zajavka.online_medical_clinic.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddVisitsDTO {
    @FutureOrPresent
    private LocalDate dateFrom;
    @FutureOrPresent
    private LocalDate dateTo;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private Integer oneVisitTime;
    private String doctorPWZ;

    public Map<String, String> asMap(){
        Map<String, String> result = new HashMap<>();

        Optional.ofNullable(dateFrom).ifPresent(val-> result.put("dateFrom", val.toString()));
        Optional.ofNullable(dateTo).ifPresent(val-> result.put("dateTo", val.toString()));
        Optional.ofNullable(timeFrom).ifPresent(val-> result.put("timeFrom", val.toString()));
        Optional.ofNullable(timeTo).ifPresent(val-> result.put("timeTo", val.toString()));
        Optional.ofNullable(oneVisitTime).ifPresent(val-> result.put("oneVisitTime", val.toString()));
        Optional.ofNullable(doctorPWZ).ifPresent(val-> result.put("doctorPWZ", val));

        return result;
    }
}
