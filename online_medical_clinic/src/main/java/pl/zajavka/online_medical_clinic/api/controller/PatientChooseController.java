package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.PeselPwzVisitNumberDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.PatientService;
import pl.zajavka.online_medical_clinic.domain.Patient;

@Controller
@RequiredArgsConstructor
public class PatientChooseController {
    private static final String PATIENT_CHOOSE = "/patient/choose";
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DoctorMapper doctorMapper;

    @GetMapping(value = PATIENT_CHOOSE)
    public String choosePatient(Model model) {
        var availableDoctors = doctorService.findAvailable().stream().map(doctorMapper::mapToDTO).toList();
        var pwzs = availableDoctors.stream().map(DoctorDTO::getPwz).toList();
        var pesels = patientService.findAvailable().stream().map(Patient::getPesel).toList();


        model.addAttribute("availableDoctorDTOs", availableDoctors);
        model.addAttribute("pwzs", pwzs);
        model.addAttribute("pesels", pesels);
        model.addAttribute("PeselPwzVisitNumberDTO", PeselPwzVisitNumberDTO.builder().build());
        return "patient_choose";
    }
}
