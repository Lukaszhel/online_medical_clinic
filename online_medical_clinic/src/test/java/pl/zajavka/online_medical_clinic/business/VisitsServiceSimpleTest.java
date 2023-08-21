package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.zajavka.online_medical_clinic.business.dao.DoctorDAO;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.time.LocalDateTime;
import java.util.List;


class VisitsServiceSimpleTest {

    DoctorDAO doctorDAO;
    DoctorService doctorService;
    VisitDAO visitDAO;
    AddVisitsService addVisitsService;

    @BeforeEach
    void methodSetUp() {
        doctorService = new DoctorService(doctorDAO);
        addVisitsService = new AddVisitsService(doctorService, visitDAO);
    }

    @Test
    void createVisitsFromDatesOfVisitsTest() {
//        given
        AddVisits addVisits = SomeFixtures.someAddVisits();
//        when
        List<LocalDateTime> result = addVisitsService.createVisitsDatesFromDoctorData(addVisits);

//        then
        Assertions.assertEquals(2, result.size());
    }


}