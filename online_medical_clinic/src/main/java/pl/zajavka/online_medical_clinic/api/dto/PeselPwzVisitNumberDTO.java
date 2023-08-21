package pl.zajavka.online_medical_clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeselPwzVisitNumberDTO {
   private String pesel;
   private String pwz;
   private String visitNumber;
}
