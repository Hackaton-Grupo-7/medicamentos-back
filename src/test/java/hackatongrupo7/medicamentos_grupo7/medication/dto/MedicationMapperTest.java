//package hackatongrupo7.medicamentos_grupo7.medication.dto;
//
//import hackatongrupo7.medicamentos_grupo7.medication.Medication;
//import hackatongrupo7.medicamentos_grupo7.user.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MedicationMapperTest {
//
//    private MedicationMapper medicationMapper;
//
//    @BeforeEach
//    void setUp() {
//        medicationMapper = Mappers.getMapper(MedicationMapper.class);
//    }
//
//    @Test
//    void testToEntity() {
//
//        MedicationRequest request = new MedicationRequest(
//                "Paracetamol",
//                500,
//                "10",
//                "Para dolor de cabeza"
//        );
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("testUser");
//
//
//        Medication medication = medicationMapper.toEntity(request, user);
//
//
//        assertNotNull(medication);
//        assertEquals("Paracetamol", medication.getName());
//        assertEquals(500, medication.getDose());
//        assertEquals("10", medication.getHour());
//        assertEquals("Para dolor de cabeza", medication.getDescription());
//        assertEquals(user, medication.getUser());
//        assertNull(medication.getId());
//    }
//
//    @Test
//    void testFromEntitySummary() {
//
//        Medication medication = new Medication();
//        medication.setName("Ibuprofeno");
//        medication.setDose(400);
//        medication.setTaken(true);
//
//
//        MedicationResponseSummary summary = medicationMapper.fromEntitySummary(medication);
//
//
//        assertNotNull(summary);
//        assertEquals("Ibuprofeno", summary.name());
//        assertEquals(400, summary.dose());
//        assertTrue(summary.taken());
//    }
//
//    @Test
//    void testFromEntityDetails() {
//
//        Medication medication = new Medication();
//        medication.setId(1L);
//        medication.setName("Aspirina");
//        medication.setDose(100);
//        medication.setHour("15");
//        medication.setTaken(false);
//        medication.setActive(true);
//        medication.setDescription("Uso general");
//        medication.setCreatedAt(LocalDateTime.of(2025, 1, 1, 12, 0));
//
//
//        MedicationResponseDetails details = medicationMapper.fromEntityDetails(medication);
//
//        assertNotNull(details);
//        assertEquals(1L, details.id());
//        assertEquals("Aspirina", details.name());
//        assertEquals(100, details.dose());
//        assertEquals("15", details.hour());
//        assertFalse(details.taken());
//        assertTrue(details.active());
//        assertEquals("Uso general", details.description());
//        assertEquals(LocalDateTime.of(2025, 1, 1, 12, 0), details.createdAt());
//    }
//}
