package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.PeselPwzVisitNumberDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.PatientService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.Patient;

@Controller
@RequiredArgsConstructor
public class PatientVisitsController {
    private static final String PATIENT_CHOOSE = "/patient/choose";
    static final String PATIENT_VISITS = "/patient/visits_by_doctor";
    static final String PATIENT_VISITS_BOOK = "/patient/patient/book/{visitNumber}";
    private static final String PATIENT_HISTORY = "/patient/history";
    private static final String PATIENT_HISTORY_CANCEL_REGISTRATION = "/patient/patient/cancel_visit/{visitNumber}";

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final VisitMapper visitMapper;
    private final DoctorMapper doctorMapper;
    //    private final PatientService patientService;

    @GetMapping(value = PATIENT_VISITS)
    public String showVisitsByDoctor(@ModelAttribute("PeselPwzVisitNumberDTO") PeselPwzVisitNumberDTO peselPwzVisitNumberDTO,
                                     Model model) {
        var doctor = doctorService.findDoctorByPWZ(peselPwzVisitNumberDTO.getPwz());
        var availableVisits = visitService.findAvailableVisitsByDoctorPwzWhereBookedIsFalse(peselPwzVisitNumberDTO.getPwz())
                .stream().map(visit -> visitMapper.mapToDTO(visit, doctor)).toList();

        model.addAttribute("visitDTOs", availableVisits);
        model.addAttribute("PeselPwzVisitNumberDTO", peselPwzVisitNumberDTO);

        return "patient_visits_by_doctor";
    }

    @PostMapping(value = PATIENT_VISITS_BOOK)
    public String bookVisit(@PathVariable String visitNumber, @ModelAttribute("PeselPwzVisitNumberDTO") PeselPwzVisitNumberDTO peselPwzVisitNumberDTO) {
        Patient patient = visitService.findPatientByPesel(peselPwzVisitNumberDTO.getPesel());

        visitService.bookVisitForPatient(visitNumber, patient);
        return "redirect:/";

    }

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

    @GetMapping(value = PATIENT_HISTORY)
    public String showHistory(@ModelAttribute("PeselPwzVisitNumberDTO") PeselPwzVisitNumberDTO peselPwzVisitNumberDTO, Model model) {
        var availableVisitsForPatientPesel = visitService.findAvailableVisitsForPatientByPesel(peselPwzVisitNumberDTO.getPesel())
                .stream().map(visitMapper::mapToDTO).toList();

        model.addAttribute("visitDTOs", availableVisitsForPatientPesel);
        return "patient_history";
    }

    @PostMapping(value = PATIENT_HISTORY_CANCEL_REGISTRATION)
    public String cancelVisit(@PathVariable String visitNumber) {

        visitService.withdrawTheRegistrationForVisit(visitNumber);
        return "redirect:/";
    }

}
