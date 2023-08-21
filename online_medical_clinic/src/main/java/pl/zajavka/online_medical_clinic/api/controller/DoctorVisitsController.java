package pl.zajavka.online_medical_clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.VisitService;

@Controller
@RequiredArgsConstructor
public class DoctorVisitsController {
    private static final String VISITS = "/visits";
    private final VisitService visitService;
    private final VisitMapper visitMapper;
    private final DoctorMapper doctorMapper;

    @GetMapping(value = VISITS)
    public String getVisits(@ModelAttribute("doctorDTO") DoctorDTO doctorDTO,
                            Model model) {
        var availableVisits = visitService.findAvailableVisitsForDoctorPwz(doctorDTO.getPwz()).stream()
                .map(visitMapper::mapToDTO)
                .toList();
        /*var availableVisits = visitService.findAvailableVisitsForDoctorPwz(doctorDTO.getPwz()).stream()
                .map(visit -> visitMapper.mapToDTO(visit,doctorMapper.mapFromDTO(doctorDTO)))
                .toList();*/
        model.addAttribute("availableVisitsDTOs", availableVisits);
        model.addAttribute("doctorDTO", doctorDTO);
//        model.addAttribute("visitDTO", VisitDTO.builder().build());

        return "visits";
    }

}
