package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.AddVisitsMapper;
import pl.zajavka.online_medical_clinic.business.AddVisitsService;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.domain.Visit;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddVisitsController {
    private static final String ADD_VISITS =  "/add_visits";
    private static final String ADD_VISITS_SAVE =  "/add_visits/save";

    private final AddVisitsService addVisitsService;
    private final AddVisitsMapper addVisitsMapper;

    @GetMapping(value = ADD_VISITS)
    public String addVisits(@ModelAttribute("doctorDTO") DoctorDTO doctorDTO, Model model){

        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("addVisitsDTO", AddVisitsDTO.builder().build());
        return "add_visits";
    }
    @PostMapping(value = ADD_VISITS_SAVE)
    public String saveVisits(@ModelAttribute("addVisitsDTO") AddVisitsDTO addVisitsDTO){
        AddVisits addVisits = addVisitsMapper.mapFromDTO(addVisitsDTO);
        List<LocalDateTime> dates = addVisitsService.createVisitsDatesFromDoctorData(addVisits);
        List<Visit> visits = addVisitsService.createVisits(dates, addVisits);
        addVisitsService.saveVisits(visits);

        return "redirect:/doctor/choose";
    }


}
