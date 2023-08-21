package pl.zajavka.online_medical_clinic.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.zajavka.online_medical_clinic.OnlineMedicalClinicApplication;
import pl.zajavka.online_medical_clinic.business.VisitService;
import pl.zajavka.online_medical_clinic.domain.Visit;
import pl.zajavka.online_medical_clinic.infrastructure.database.entity.VisitEntity;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.jpa.VisitJpaRepository;
import pl.zajavka.online_medical_clinic.infrastructure.database.repository.mapper.VisitEntityMapper;
import pl.zajavka.online_medical_clinic.util.SomeFixtures;

import java.util.List;

@Testcontainers
@SpringJUnitConfig(classes = {OnlineMedicalClinicApplication.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VisitServiceTestIT {
   /* @Container
    static PostgreSQLContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:15.0");

    @DynamicPropertySource
    @SuppressWarnings("unused")
    static void postgresqlContainerProperties(DynamicPropertyRegistry registry) {
        registry.add("jdbc.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("jdbc.user", POSTGRESQL_CONTAINER::getUsername);
        registry.add("jdbc.pass", POSTGRESQL_CONTAINER::getPassword);
    }


    private VisitService visitService;
    private VisitJpaRepository visitJpaRepository;
    private VisitEntityMapper visitEntityMapper;

    @Test
    void thatUpdateVisitWithCommentByVisitNumberWorksCorrectlyIT() {
//        given
        Visit someVisit1 = SomeFixtures.someVisit1();
        VisitEntity visitEntity1 = visitEntityMapper.mapToEntity(someVisit1);
        visitJpaRepository.save(visitEntity1);
        visitService.updateVisitWithCommentByVisitNumber("new comment for test", someVisit1.getVisitNumber());
//        when
        List<Visit> visitList = visitService.findAvailableVisitsForDoctorPwz(SomeFixtures.someDoctor1().getPwz()).stream()
                .filter(visit -> visit.getComment().equals(someVisit1.getComment())).toList();

//        then
        Assertions.assertEquals("new comment for test", visitList.get(0).getComment());
    }

*/
}
