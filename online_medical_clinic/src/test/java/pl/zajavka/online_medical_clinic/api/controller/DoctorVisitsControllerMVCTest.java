package pl.zajavka.online_medical_clinic.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DoctorVisitsController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorVisitsControllerMVCTest {

    private MockMvc mockMvc;

    @MockBean
    private VisitMapper visitMapper;

    @MockBean
    private VisitService visitService;

    @MockBean
    private DoctorMapper doctorMapper;

    @MockBean
    private AddVisitsService addVisitsService;

    @MockBean
    private AddVisitsMapper addVisitsMapper;

    @MockBean
    private DoctorService doctorService;


    @Test
    void thatGetVisitsWorksCorrectly() throws Exception {
//        given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        SomeFixtures.someDoctorDTO1().asMap().forEach(parameters::add);

//        Mockito.when(visitMapper.mapToDTO(SomeFixtures.someVisit1())).thenReturn(SomeFixtures.someVisitDTO1());
//        Mockito.when(visitService.findAvailableVisitsForDoctorPwz(SomeFixtures.someDoctor1().getPwz())).thenReturn(List.of(SomeFixtures.someVisit1()));
//        when, then
        mockMvc.perform(get(DoctorVisitsController.VISITS).params(parameters))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("availableVisitsDTOs"))
                .andExpect(model().attributeExists("doctorDTO"))
                .andExpect((view().name("visits")));

    }

    @ParameterizedTest
    @MethodSource
    void thatAddVisitsValidationWorksCorrectly(String correctAddVisits, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo) throws Exception {
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        AddVisitsDTO.builder()
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .timeFrom(timeFrom)
                .timeTo(timeTo)
//                .oneVisitTime(oneVisitTime)
//                .doctorPWZ("1234567")
                .build().asMap().forEach(parameters::add);

        switch (correctAddVisits) {
            case "true" -> mockMvc.perform(post(DoctorVisitsController.ADD_VISITS_SAVE).params(parameters))
                    .andExpect(status().isFound())
                    .andExpect(model().attributeDoesNotExist("errorMessage"))
                    .andExpect(view().name("redirect:/doctor/choose"));
            case "false - invalid MVC validation" ->
                    mockMvc.perform(post(DoctorVisitsController.ADD_VISITS_SAVE).params(parameters))
                            .andExpect(status().isBadRequest())
                            .andExpect(view().name("error"))
                            .andExpect(model().attributeExists("errorMessage"));
            case "false - invalid inMethod validation" ->
                    mockMvc.perform(post(DoctorVisitsController.ADD_VISITS_SAVE).params(parameters))
                            .andExpect(status().isInternalServerError())
                            .andExpect(view().name("error"))
                            .andExpect(model().attributeExists("errorMessage"));
        }
    }
    public static Stream<Arguments> thatAddVisitsValidationWorksCorrectly(){
        return Stream.of(
          Arguments.of("true", LocalDate.now(), LocalDate.now(), LocalTime.of(13,0), LocalTime.of(13,10)),
          Arguments.of("false - invalid inMethod validation", LocalDate.now().plusDays(1), LocalDate.now(), LocalTime.of(13,0), LocalTime.of(13,10)),
          Arguments.of("false - invalid inMethod validation", LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), LocalTime.of(13,10), LocalTime.of(13,0)),
          Arguments.of("false - invalid MVC validation", LocalDate.now().minusDays(1), LocalDate.now().minusDays(2), LocalTime.of(13,0), LocalTime.of(13,10)),
          Arguments.of("false - invalid MVC validation", LocalDate.now().minusDays(1), LocalDate.now(), LocalTime.of(13,0), LocalTime.of(13,10))
        );
    }

}
