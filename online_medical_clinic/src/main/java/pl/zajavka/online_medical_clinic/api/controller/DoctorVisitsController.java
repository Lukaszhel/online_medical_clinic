package pl.zajavka.online_medical_clinic.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.AddVisitsMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.AddVisitsService;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.domain.exception.ProcessingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorVisitsController {
    private static final String DOCTOR_CHOOSE = "/doctor/choose";
    static final String VISITS = "/visits";
    private static final String ADD_VISITS = "/add_visits";
    static final String ADD_VISITS_SAVE = "/add_visits/save";
    private static final String ADD_COMMENT = "/add-comment/{visitNumber}";
    private static final String ADD_COMMENT_SAVE = "/add-comment/save";

    private final VisitService visitService;
    private final VisitMapper visitMapper;
    private final DoctorMapper doctorMapper;
    private final AddVisitsService addVisitsService;
    private final AddVisitsMapper addVisitsMapper;
    private final DoctorService doctorService;

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

    @GetMapping(value = VISITS)
    public String getVisits(@ModelAttribute("doctorDTO") DoctorDTO doctorDTO,
                            Model model) {
        var availableVisits = visitService.findAvailableVisitsForDoctorPwz(doctorDTO.getPwz()).stream()
                .map(visitMapper::mapToDTO)
                .toList();
        model.addAttribute("availableVisitsDTOs", availableVisits);
        model.addAttribute("doctorDTO", doctorDTO);
//        model.addAttribute("visitDTO", VisitDTO.builder().build());

        return "visits";
    }

    @GetMapping(value = ADD_COMMENT)
    public String addComment(@PathVariable String visitNumber, Model model) {

        model.addAttribute("visitDTO", VisitDTO.builder().visitNumber(visitNumber).build());
        return "add_comment";

    }

    @GetMapping(value = ADD_COMMENT_SAVE)
    public String saveComment(@ModelAttribute("visitDTO") VisitDTO visitDTO) {
        visitService.addCommentToVisitByVisitNumber(visitDTO.getComment(), visitDTO.getVisitNumber());
        return "redirect:/doctor/choose";
    }

    @GetMapping(value = ADD_VISITS)
    public String addVisits(@ModelAttribute("doctorDTO") DoctorDTO doctorDTO, Model model) {

        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("addVisitsDTO", AddVisitsDTO.builder().build());
        return "add_visits";
    }

    @PostMapping(value = ADD_VISITS_SAVE)
    public String saveVisits(@Valid @ModelAttribute("addVisitsDTO") AddVisitsDTO addVisitsDTO) {
        LocalDate dateFrom = addVisitsDTO.getDateFrom();
        LocalTime timeFrom = addVisitsDTO.getTimeFrom();
        LocalDate dateTo = addVisitsDTO.getDateTo();
        LocalTime timeTo = addVisitsDTO.getTimeTo();

        if (LocalDateTime.of(dateFrom, timeFrom).isAfter(LocalDateTime.of(dateTo, timeTo))) {
            throw new ProcessingException(
                    String.format("The entered data shows that the end date and time: [%s] [%s] of the doctor's " +
                                    "admission is earlier than the start date and time: [%s] [%s].",
                            dateTo, timeTo, dateFrom, timeFrom));
        }

        AddVisits addVisits = addVisitsMapper.mapFromDTO(addVisitsDTO);
        List<LocalDateTime> dates = addVisitsService.createVisitsDatesFromDoctorData(addVisits);
        List<Visit> visits = addVisitsService.createVisits(dates, addVisitsDTO.getDoctorPWZ());
        addVisitsService.saveVisits(visits);

        return "redirect:/doctor/choose";
    }

}
