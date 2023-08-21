package pl.zajavka.online_medical_clinic.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.domain.Visit;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AddVisitsService {

    private final DoctorService doctorService;
    private final VisitDAO visitDAO;

    public List<LocalDateTime> createVisitsDatesFromDoctorData(AddVisits visits) {
        Integer daysOfVisitsInARow = calculateDaysOfVisitsInARow(visits);
        Integer minutesOfVisitsInOneDay = calculateMinutesOfVisitsInOneDay(visits);
        Integer amountOfVisitsInOneDay = calculateAmountOfVisitsInOneDay(visits, Float.valueOf(minutesOfVisitsInOneDay));
        LocalDateTime tempDateTime = LocalDateTime.of(visits.getDateFrom(), visits.getTimeFrom());
        List<LocalDateTime> result = new ArrayList<>();
        for (int i = 1; i <= daysOfVisitsInARow; i++) {
            for (int j = 1; j <= amountOfVisitsInOneDay; j++) {
                result.add(tempDateTime);
                tempDateTime = tempDateTime.plusMinutes((long)visits.getOneVisitTime());
            }
            tempDateTime = LocalDateTime.of(visits.getDateFrom(), visits.getTimeFrom()).plusDays(i);
        }
        return result;
    }
    private static Integer calculateDaysOfVisitsInARow(AddVisits visits) {
        return Period.between(visits.getDateFrom(), visits.getDateTo()).getDays() + 1;
    }
    private static Integer calculateAmountOfVisitsInOneDay(AddVisits visits, Float minutesOfVisitsInOneDay) {
        return (int) (minutesOfVisitsInOneDay / visits.getOneVisitTime());
    }
    private static Integer calculateMinutesOfVisitsInOneDay(AddVisits visits) {
        return (visits.getTimeTo().toSecondOfDay() - visits.getTimeFrom().toSecondOfDay()) / 60;
    }
    public List<Visit> createVisits(List<LocalDateTime> datesOfVisits, AddVisits visits) {
        log.info("createVisits method");
        return datesOfVisits.stream()
                .map(date -> Visit.builder()
                        .visitNumber(UUID.randomUUID().toString())
                        .dateTime(OffsetDateTime.of(date, ZoneOffset.ofHours(1)))
                        .booked(false)
                        .doctor(doctorService.findDoctorByPWZ(visits.getDoctorPWZ()))
                        .build())
                .toList();
    }
    @Transactional
    public void saveVisits(List<Visit> createdVisits) {
        log.info("saveVisits method");
        for (Visit createdVisit : createdVisits) {
            visitDAO.saveVisitFromVisits(createdVisit);
        }
    }
}
