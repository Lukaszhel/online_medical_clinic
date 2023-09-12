package pl.zajavka.online_medical_clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import pl.zajavka.online_medical_clinic.api.dto.PeselPwzVisitNumberDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.DoctorMapper;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.DoctorService;
import pl.zajavka.online_medical_clinic.business.PatientService;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.Doctor;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientVisitsControllerTest {

    @Mock
    private VisitService visitService;
    @Mock
    private DoctorService doctorService;
    @Mock
    private PatientService patientService;
    @Mock
    private VisitMapper visitMapper;
    @Mock
    private DoctorMapper doctorMapper;
    @InjectMocks
    private PatientVisitsController patientVisitsController;

    @Test
    void thatShowVisitsByDoctorWorksCorrectly() {
//        given
        PeselPwzVisitNumberDTO peselPwzVisitNumberDTO = SomeFixtures.somePeselPwzVisitNumberDTO();
        List<VisitDTO> visitDTOS = List.of(SomeFixtures.someVisitDTO1());
        Visit someVisit1 = SomeFixtures.someVisit1();
        Doctor someDoctor1 = SomeFixtures.someDoctor1();
        ExtendedModelMap model = new ExtendedModelMap();

//        Mockito.when(doctorService.findDoctorByPWZ(peselPwzVisitNumberDTO.getPwz())).thenReturn(SomeFixtures.someDoctor1());
        when(doctorService.findDoctorByPWZ(anyString())).thenReturn(someDoctor1);
        when(visitMapper.mapToDTO(someVisit1, someDoctor1)).thenReturn(SomeFixtures.someVisitDTO1());
        when(visitService.findAvailableVisitsByDoctorPwzWhereBookedIsFalse(peselPwzVisitNumberDTO.getPwz())).thenReturn(List.of(someVisit1));
//        when
        String result = patientVisitsController.showVisitsByDoctor(peselPwzVisitNumberDTO, model);
//        then
        assertThat(result).isEqualTo("patient_visits_by_doctor");
        assertThat(model.getAttribute("visitDTOs")).isEqualTo(visitDTOS);
        assertThat(model.getAttribute("PeselPwzVisitNumberDTO")).isEqualTo(peselPwzVisitNumberDTO);
    }

    @Test
    void thatBookVisitWorksCorrectly() {
//        given
        PeselPwzVisitNumberDTO peselPwzVisitNumberDTO = SomeFixtures.somePeselPwzVisitNumberDTO();
        String visitNumber = SomeFixtures.someVisit1().getVisitNumber();
        when(visitService.findPatientByPesel(peselPwzVisitNumberDTO.getPesel())).thenReturn(SomeFixtures.somePatient1());

//        when
        String result = patientVisitsController.bookVisit(visitNumber, peselPwzVisitNumberDTO);
//        then
        assertThat(result).isEqualTo("redirect:/");
    }

    @Test
    void tahtChoosePatientWorksCorrectly() {
//        given
        ExtendedModelMap model = new ExtendedModelMap();
        when(doctorMapper.mapToDTO(any(Doctor.class))).thenReturn(SomeFixtures.someDoctorDTO1());
        when(doctorService.findAvailable()).thenReturn(List.of(SomeFixtures.someDoctor1()));
        when(patientService.findAvailable()).thenReturn(List.of(SomeFixtures.somePatient1()));
//        when
        String result = patientVisitsController.choosePatient(model);
//        then
        assertThat(result).isEqualTo("patient_choose");
        assertThat(model.getAttribute("availableDoctorDTOs")).isEqualTo(List.of(SomeFixtures.someDoctorDTO1()));
        assertThat(model.getAttribute("pwzs")).isEqualTo(List.of(SomeFixtures.someDoctor1().getPwz()));
        assertThat(model.getAttribute("pesels")).isEqualTo(List.of(SomeFixtures.somePatient1().getPesel()));
        assertThat(model.getAttribute("PeselPwzVisitNumberDTO")).isEqualTo(PeselPwzVisitNumberDTO.builder().build());
    }
    @Test
    void showHistory() {
        //        given
        ExtendedModelMap model = new ExtendedModelMap();
        PeselPwzVisitNumberDTO peselPwzVisitNumberDTO = SomeFixtures.somePeselPwzVisitNumberDTO();
        VisitDTO visitDTO = SomeFixtures.someVisitDTO1();

        when(visitMapper.mapToDTO(any(Visit.class))).thenReturn(visitDTO);
        when(visitService.findAvailableVisitsForPatientByPesel(anyString())).thenReturn(List.of(SomeFixtures.someVisit1()));
//        when
        String result = patientVisitsController.showHistory(peselPwzVisitNumberDTO, model);
//        then
        assertThat(result).isEqualTo("patient_history");
        assertThat(model.getAttribute("visitDTOs")).isEqualTo(List.of(visitDTO));

    }
    @Test
    void testCancelVisit() {
//        given
        String visitNumber = SomeFixtures.someVisit1().getVisitNumber();
//        when
        String result = patientVisitsController.cancelVisit(visitNumber);
//        then
        assertThat(result).isEqualTo("redirect:/");
    }
}