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
public class DoctorDTO {

    private String name;
    private String surname;
    private String specialization;
    private String pwz;

    public Map<String, String> asMap() {
        Map<String, String> result = new HashMap<>();
        Optional.ofNullable(name).ifPresent(val -> result.put("name", val));
        Optional.ofNullable(surname).ifPresent(val -> result.put("surname", val));
        Optional.ofNullable(specialization).ifPresent(val -> result.put("specialization", val));
        Optional.ofNullable(pwz).ifPresent(val -> result.put("pwz", val));
        return result;
    }
}
