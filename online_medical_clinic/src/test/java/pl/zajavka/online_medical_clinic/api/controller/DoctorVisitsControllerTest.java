package pl.zajavka.online_medical_clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
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
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorVisitsControllerTest {
    @Mock
    private VisitService visitService;
    @Mock
    private VisitMapper visitMapper;
    @Mock
    private DoctorService doctorService;
    @Mock
    private DoctorMapper doctorMapper;
    @Mock
    private AddVisitsService addVisitsService;
    @Mock
    private AddVisitsMapper addVisitsMapper;
    @InjectMocks
    private DoctorVisitsController doctorVisitsController;

    @Test
    void thatRetrievingVisitsWorksCorrectly() {
//        given
        DoctorDTO doctorDTO = SomeFixtures.someDoctorDTO1();
        List<VisitDTO> listVisitDTO = List.of(SomeFixtures.someVisitDTO1());
        ExtendedModelMap model = new ExtendedModelMap();

        when(visitMapper.mapToDTO(SomeFixtures.someVisit1())).thenReturn(listVisitDTO.get(0));
        when(visitService.findAvailableVisitsForDoctorPwz(doctorDTO.getPwz())).thenReturn(List.of(SomeFixtures.someVisit1()));

//        when
        String result = doctorVisitsController.getVisits(doctorDTO, model);
//        then
        assertThat(result).isEqualTo("visits");
        assertThat(model.getAttribute("doctorDTO")).isEqualTo(doctorDTO);
        assertThat(model.getAttribute("availableVisitsDTOs")).isEqualTo(listVisitDTO);
    }

    @Test
    void thatDoctorChoosingWorksCorrectly() {
//        given
        ExtendedModelMap model = new ExtendedModelMap();
        when(doctorMapper.mapToDTO(SomeFixtures.someDoctor1())).thenReturn(SomeFixtures.someDoctorDTO1());
        when(doctorService.findAvailable()).thenReturn(List.of(SomeFixtures.someDoctor1()));
//        when
        String result = doctorVisitsController.chooseDoctor(model);
//        then
        assertThat(result).isEqualTo("doctor_choose");
        assertThat(model.getAttribute("pwzs")).isEqualTo(List.of(SomeFixtures.someDoctor1().getPwz()));
        assertThat(model.getAttribute("doctorDTO")).isEqualTo(DoctorDTO.builder().build());

    }

    @Test
    void thatAddCommentWorksCorrectly() {
//        given
        String visitNumber = SomeFixtures.someVisitDTO1().getVisitNumber();
        ExtendedModelMap model = new ExtendedModelMap();
//        when
        String result = doctorVisitsController.addComment(visitNumber, model);
//        then
        assertThat(result).isEqualTo("add_comment");
        assertThat(model.getAttribute("visitDTO")).isEqualTo(VisitDTO.builder().visitNumber(visitNumber).build());

    }

    @Test
    void thatSaveCommentWorksCorrectly() {
//        given
        VisitDTO visitDTO = SomeFixtures.someVisitDTO1();
//        when
        String result = doctorVisitsController.saveComment(visitDTO);
//        then
        assertThat(result).isEqualTo("redirect:/doctor/choose");
    }

    @Test
    void thatAddVisitsWorksCorrectly() {
        //        given
        DoctorDTO doctorDTO = SomeFixtures.someDoctorDTO1();
        ExtendedModelMap modelMap = new ExtendedModelMap();
//        when
        String result = doctorVisitsController.addVisits(doctorDTO, modelMap);
//        then
        assertThat(result).isEqualTo("add_visits");
        assertThat(modelMap.getAttribute("doctorDTO")).isEqualTo(doctorDTO);
        assertThat(modelMap.getAttribute("addVisitsDTO")).isEqualTo(AddVisitsDTO.builder().build());
    }

    @Test
    void thatSaveVisitWorksCorrectly() {
        //        given
        AddVisitsDTO addVisitsDTO = SomeFixtures.someAddVisitsDTO1();
        AddVisits addVisits = SomeFixtures.someAddVisits1();
        List<LocalDateTime> dates = List.of(LocalDateTime.of(addVisits.getDateFrom(), addVisits.getTimeFrom()));
        List<Visit> visits = List.of(SomeFixtures.someVisit1());

//        Mockito.when(addVisitsMapper.mapFromDTO(addVisitsDTO)).thenReturn(addVisits);
        when(addVisitsMapper.mapFromDTO(any(AddVisitsDTO.class))).thenReturn(addVisits);
        when(addVisitsService.createVisitsDatesFromDoctorData(addVisits)).thenReturn(dates);
        when(addVisitsService.createVisits(dates, addVisits)).thenReturn(visits);
//        when
        String result = doctorVisitsController.saveVisits(addVisitsDTO);
//        then
        assertThat(result).isEqualTo("redirect:/doctor/choose");

    }
}