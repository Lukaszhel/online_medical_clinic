package pl.zajavka.online_medical_clinic.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.zajavka.online_medical_clinic.api.dto.AddVisitsDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.AddVisitsMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.AddVisitsService;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.PatientService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.Patient;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PatientVisitsController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientVisitsControllerMVCTest {

    private MockMvc mockMvc;

    @MockBean
    private VisitService visitService;
    @MockBean
    private DoctorService doctorService;
    @MockBean
    private PatientService patientService;
    @MockBean
    private VisitMapper visitMapper;
    @MockBean
    private DoctorMapper doctorMapper;

    @Test
    void thatShowVisitsByDoctorWorksCorrectly() throws Exception {
//        given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        SomeFixtures.somePeselPwzVisitNumberDTO().asMap().forEach(parameters::add);
        when(doctorService.findDoctorByPWZ(anyString())).thenReturn(SomeFixtures.someDoctor1());
        when(visitMapper.mapToDTO(any(Visit.class), any(Doctor.class))).thenReturn(SomeFixtures.someVisitDTO1());
        when(visitService.findAvailableVisitsByDoctorPwzWhereBookedIsFalse(anyString())).thenReturn(List.of(SomeFixtures.someVisit1()));
//        when
//        then
        mockMvc.perform(get(PatientVisitsController.PATIENT_VISITS).params(parameters))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visitDTOs"))
                .andExpect(model().attributeExists("PeselPwzVisitNumberDTO"))
                .andExpect(view().name("patient_visits_by_doctor"));
    }

    @Test
    void
    thatBookVisitWorksCorrectly() throws Exception {
//        given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        SomeFixtures.somePeselPwzVisitNumberDTO().asMap().forEach((key, value) -> parameters.add(key, value));
        when(visitService.findPatientByPesel(anyString())).thenReturn(SomeFixtures.somePatient1());
//        when
//        then
        mockMvc.perform(post(PatientVisitsController.PATIENT_VISITS_BOOK, SomeFixtures.someVisitDTO1().getVisitNumber()).params(parameters))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"));

    }


}
