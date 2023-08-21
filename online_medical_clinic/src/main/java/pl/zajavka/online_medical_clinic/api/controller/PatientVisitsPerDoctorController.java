package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.online_medical_clinic.api.dto.PeselPwzVisitNumberDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.Patient;

@Controller
@RequiredArgsConstructor
public class PatientVisitsPerDoctorController {
    private static final String PATIENT_VISITS = "/patient/visits_by_doctor";
    private static final String PATIENT_VISITS_BOOK = "/patient/patient/book/{visitNumber}";
    private final VisitService visitService;
    private final DoctorService doctorService;

    private final VisitMapper visitMapper;

    @GetMapping(value = PATIENT_VISITS)
    public String showVisitsByDoctor(@ModelAttribute("PeselPwzVisitNumberDTO") PeselPwzVisitNumberDTO peselPwzVisitNumberDTO,
                                     Model model) {
        var doctor = doctorService.findDoctorByPWZ(peselPwzVisitNumberDTO.getPwz());
        var availableVisits = visitService.findAvailableVisitsByDoctorPwzWhereBookedIsfalse(peselPwzVisitNumberDTO.getPwz())
                .stream().map(visit -> visitMapper.mapToDTO(visit, doctor)).toList();

        model.addAttribute("visitDTOs", availableVisits);
        model.addAttribute("PeselPwzVisitNumberDTO", peselPwzVisitNumberDTO);

        return "patient_visits_by_doctor";
    }

    @PostMapping(value = PATIENT_VISITS_BOOK)
    public String bookVisit(@PathVariable String visitNumber, @ModelAttribute("PeselPwzVisitNumberDTO") PeselPwzVisitNumberDTO peselPwzVisitNumberDTO) {
        Patient patient = visitService.findPatientByPesel(peselPwzVisitNumberDTO.getPesel());

        visitService.updateBookVisitForPatient(visitNumber, patient);
        return "redirect:/";

    }

}
