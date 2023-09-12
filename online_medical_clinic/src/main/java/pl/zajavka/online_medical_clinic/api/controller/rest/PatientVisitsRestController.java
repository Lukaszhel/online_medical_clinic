package pl.zajavka.online_medical_clinic.api.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.Patient;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(PatientVisitsRestController.API_PATIENT)
public class PatientVisitsRestController {
    public static final String API_PATIENT = "/api/patient";
    public static final String PATIENT_VISITS_BY_DOCTOR = "/{doctorPWZ}";
    public static final String PATIENT_BOOK_VISIT = "/book/{visitNumber}";
    public static final String PATIENT_SHOW_HISTORY = "/show_history";
    public static final String PATIENT_CANCEL_REGISTRATION = "/cancel/{visitNumber}";


    private final VisitService visitService;
    private final DoctorService doctorService;
    private final VisitMapper visitMapper;

    @GetMapping(value = PATIENT_VISITS_BY_DOCTOR)
    public VisitsDTO showVisitsByDoctor(@PathVariable String doctorPwz) {

        return findVisitsByDoctorToShow(doctorPwz);
    }

    @PatchMapping(value = PATIENT_BOOK_VISIT)
    public ResponseEntity<?> bookVisit(
            @PathVariable String visitNumber,
            @RequestParam(required = true) String pesel) {

        Patient patient = visitService.findPatientByPesel(pesel);
        visitService.bookVisitForPatient(visitNumber, patient);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = PATIENT_SHOW_HISTORY)
    public VisitsDTO showHistory(@RequestParam(required = true) String pesel) {

        return findVisitsByPatientPeselToShowHistory(pesel);
    }

    @PatchMapping(value = PATIENT_CANCEL_REGISTRATION)
    public ResponseEntity<?> cancelRegistration(@PathVariable String visitNumber) {

        visitService.withdrawTheRegistrationForVisit(visitNumber);

        return ResponseEntity.ok().build();
    }


    private VisitsDTO findVisitsByDoctorToShow(String doctorPwz) {

        return VisitsDTO.builder()
                .visits(findVisitsByDoctor(doctorPwz))
                .build();
    }

    private List<VisitDTO> findVisitsByDoctor(String doctorPwz) {

        Doctor doctor = doctorService.findDoctorByPWZ(doctorPwz);

        return visitService.findAvailableVisitsByDoctorPwzWhereBookedIsFalse(doctorPwz).stream()
                .map(visit -> visitMapper.mapToDTO(visit, doctor)).toList();
    }

    private VisitsDTO findVisitsByPatientPeselToShowHistory(String pesel) {

        return VisitsDTO.builder()
                .visits(findVisitsByPeselToShowHistory(pesel))
                .build();
    }

    private List<VisitDTO> findVisitsByPeselToShowHistory(String pesel) {

        return visitService.findAvailableVisitsForPatientByPesel(pesel).stream()
                .map(visitMapper::mapToDTO).toList();
    }
}
