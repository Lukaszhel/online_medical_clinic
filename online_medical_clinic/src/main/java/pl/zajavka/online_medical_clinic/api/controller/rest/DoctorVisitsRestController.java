package pl.zajavka.online_medical_clinic.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.AddVisitsMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.AddVisitsService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.domain.Visit;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(DoctorVisitsRestController.API_DOCTOR)
public class DoctorVisitsRestController {
    public static final String API_DOCTOR = "/api/doctor";
    public static final String DOCTOR_PWZ_SHOW_VISITS = "/show_visits/{doctorPWZ}";
    public static final String DOCTOR_PWZ_ADD_VISITS = "/add_visits/{doctorPWZ}";
    public static final String DOCTOR_ADD_COMMENT = "/add_comment/{visitNumber}";


    private final VisitService visitService;
    private final AddVisitsService addVisitsService;
    private final VisitMapper visitMapper;
    private final AddVisitsMapper addVisitsMapper;

    @GetMapping(value = DOCTOR_PWZ_SHOW_VISITS)
    public VisitsDTO visitsList(@PathVariable String doctorPwz) {

        return showAvailableVisitsByDoctorPWZ(doctorPwz);
    }

    @PostMapping(value = DOCTOR_PWZ_ADD_VISITS)
    public VisitsDTO addVisits(
            @PathVariable String doctorPwz,
            @RequestBody AddVisitsDTO addVisitsDTO) {

        return addAndReturnVisitsDTO(addVisitsDTO, doctorPwz);
    }


    @PatchMapping(value = DOCTOR_ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String visitNumber,
                                        @RequestBody String comment) {

        visitService.updateVisitWithCommentByVisitNumber(comment, visitNumber);

        return ResponseEntity.ok().build();
    }


    private VisitsDTO showAvailableVisitsByDoctorPWZ(String doctorPwz) {

        return VisitsDTO.builder()
                .visits(getAvailableVisits(doctorPwz))
                .build();
    }

    private List<VisitDTO> getAvailableVisits(String doctorPwz) {

        return visitService.findAvailableVisitsForDoctorPwz(doctorPwz).stream()
                .map(visitMapper::mapToDTO).toList();
    }

    private VisitsDTO addAndReturnVisitsDTO(AddVisitsDTO addVisitsDTO, String doctorPwz) {

        return VisitsDTO.builder()
                .visits(createAndGetVisits(addVisitsDTO, doctorPwz))
                .build();
    }

    private List<VisitDTO> createAndGetVisits(AddVisitsDTO addVisitsDTO, String doctorPwz) {

        AddVisits addVisits = addVisitsMapper.mapFromDTO(addVisitsDTO);
        List<LocalDateTime> dates = addVisitsService.createVisitsDatesFromDoctorData(addVisits);
        List<Visit> visits = addVisitsService.createVisits(dates, addVisits);
        addVisitsService.saveVisits(visits);

        return visitService.findAvailableVisitsForDoctorPwz(doctorPwz).stream()
                .map(visitMapper::mapToDTO).toList();
    }
}
