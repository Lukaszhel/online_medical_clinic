package pl.zajavka.online_medical_clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import pl.zajavka.online_medical_clinic.api.dto.DoctorDTO;
import pl.zajavka.online_medical_clinic.api.dto.VisitDTO;
import pl.zajavka.online_medical_clinic.api.dto.mapper.VisitMapper;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DoctorVisitsControllerTest {
    /*@Mock
    private VisitService visitService;
    @Mock
    private VisitMapper visitMapper;
    @InjectMocks
    private DoctorVisitsController doctorVisitsController;

    @Test
    void thatRetrievingVisitsWorksCorrectly() {
//        given
        DoctorDTO doctorDTO = SomeFixtures.someDoctorDTO1();
        List<VisitDTO> listVisitDTO = List.of(SomeFixtures.someVisitDTO1());
        ExtendedModelMap model = new ExtendedModelMap();

        Mockito.when(visitService.findAvailableVisitsForDoctorPwz(doctorDTO.getPwz()).stream()
                .map(visit -> visitMapper.mapToDTO(visit,SomeFixtures.someDoctor1())).toList())
                .thenReturn(List.of(SomeFixtures.someVisitDTO1()));

//        when
        String result = doctorVisitsController.getVisits(doctorDTO, model);
//        then
        assertThat(result).isEqualTo("visits");
        assertThat(model.getAttribute("doctorDTO")).isEqualTo(doctorDTO);
//        assertThat(model.getAttribute("availableVisitsDTOs")).isEqualTo(listVisitDTO);
    }*/

}