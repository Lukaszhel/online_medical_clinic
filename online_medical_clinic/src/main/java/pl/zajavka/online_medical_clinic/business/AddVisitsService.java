package pl.zajavka.online_medical_clinic.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.online_medical_clinic.business.dao.VisitDAO;
import pl.zajavka.online_medical_clinic.domain.AddVisits;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.domain.exception.ProcessingException;

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

    public List<LocalDateTime> createVisitsDatesFromDoctorData(AddVisits addVisits) {
        Integer oneVisitTime = addVisits.getOneVisitTime();
        LocalDateTime dateTimeFrom = LocalDateTime.of(addVisits.getDateFrom(), addVisits.getTimeFrom());
        LocalDateTime dateTimeTo = LocalDateTime.of(addVisits.getDateTo(), addVisits.getTimeTo());

        int daysOfVisitsInARow = calculateDaysOfVisitsInARow(addVisits);
        Integer minutesOfVisitsInOneDay = calculateMinutesOfVisitsInOneDay(addVisits);
        Integer amountOfVisitsInOneDay = calculateAmountOfVisitsInOneDay(addVisits, Float.valueOf(minutesOfVisitsInOneDay));
        LocalDateTime tempDateTime = dateTimeFrom;

        List<LocalDateTime> result = new ArrayList<>();
        for (int i = 1; i <= daysOfVisitsInARow; i++) {
            for (int j = 1; j <= amountOfVisitsInOneDay; j++) {
                result.add(tempDateTime);
                tempDateTime = tempDateTime.plusMinutes((long) oneVisitTime);
            }
            tempDateTime = dateTimeFrom.plusDays(i);
        }
        if (result.size() == 0) {
            throw new ProcessingException(String.format("Failed to create any visit. The time of one visit is: [%s]," +
                            " the entered data shows that the time between the start of patient admission: [%s] and " +
                            "its end: [%s] is too short to create at least one visit.",
                    oneVisitTime, dateTimeFrom, dateTimeTo));
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

    public List<Visit> createVisits(List<LocalDateTime> datesOfVisits, String pwz) {
        log.info("createVisits method");
        return datesOfVisits.stream()
                .map(dateTime -> Visit.builder()
                        .visitNumber(UUID.randomUUID().toString())
                        .dateTime(OffsetDateTime.of(dateTime, ZoneOffset.ofHours(1)))
                        .booked(false)
                        .doctor(doctorService.findDoctorByPWZ(pwz))
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
