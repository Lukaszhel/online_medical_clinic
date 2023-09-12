package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.business.DoctorService;

@Controller
@RequiredArgsConstructor
public class DoctorChooseController {
   /* private static final String DOCTOR_CHOOSE = "/doctor/choose";

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;


    @GetMapping(value = DOCTOR_CHOOSE)
    public String chooseDoctor(Model model) {
        var pwzs = doctorService.findAvailable().stream()
                .map(doctorMapper::mapToDTO)
                .map(DoctorDTO::getPwz)
                .toList();

        model.addAttribute("pwzs", pwzs);
        model.addAttribute("doctorDTO", DoctorDTO.builder().build());

        return "doctor_choose";

    }

*/


}
