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
import pl.zajavka.online_medical_clinic.business.VisitService;

@Controller
@RequiredArgsConstructor
public class PatientHistory {
    /*private static final String PATIENT_HISTORY = "/patient/history";
    private static final String PATIENT_HISTORY_CANCEL = "/patient/patient/cancel_visit/{visitNumber}";
    private final VisitService visitService;
    //    private final PatientService patientService;
    private final VisitMapper visitMapper;

    @GetMapping(value = PATIENT_HISTORY)
    public String showHistory(@ModelAttribute("PeselPwzVisitNumberDTO") PeselPwzVisitNumberDTO peselPwzVisitNumberDTO, Model model) {
        var availableVisitsForPatientPesel = visitService.findAvailableVisitsForPatientByPesel(peselPwzVisitNumberDTO.getPesel())
                .stream().map(visitMapper::mapToDTO).toList();

        model.addAttribute("visitDTOs", availableVisitsForPatientPesel);
        return "patient_history";
    }

    @PostMapping(value = PATIENT_HISTORY_CANCEL)
    public String bookVisit(@PathVariable String visitNumber) {

        visitService.withdrawTheRegistrationForVisit(visitNumber);
        return "redirect:/";
    }*/

}
