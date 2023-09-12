package pl.zajavka.online_medical_clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddVisitsServiceTest {
    @Mock
    private DoctorService doctorService;
    @Mock
    private VisitDAO visitDAO;
    @InjectMocks
    private AddVisitsService addVisitsService;

    @Test
    void createVisitsDatesFromDoctorData() {
    }

    @Test
    void createVisits() {
    }

    @Test
    void saveVisits() {
    }
}