package pl.zajavka.online_medical_clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeselPwzVisitNumberDTO {
    private String pesel;
    private String pwz;
    private String visitNumber;

    public Map<String, String> asMap() {
        Map<String, String> result = new HashMap<>();
        Optional.ofNullable(pesel).ifPresent(val -> result.put("pesel", val));
        Optional.ofNullable(pwz).ifPresent(val -> result.put("pwz", val));
        Optional.ofNullable(visitNumber).ifPresent(val -> result.put("visitNumber", val));
        return result;
    }
}
