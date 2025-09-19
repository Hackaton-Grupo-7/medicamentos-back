//package hackatongrupo7.medicamentos_grupo7.medication;
//
//import hackatongrupo7.medicamentos_grupo7.user.User;
//import hackatongrupo7.medicamentos_grupo7.user.UserRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class MedicationRepositoryTest {
//
//    @Autowired
//    private MedicationRepository medicationRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//    @Test
//    @DisplayName("findByUserId debería devolver medicamentos de un usuario específico")
//    void testFindByUserId() {
//        User user = new User();
//        user.setUsername("johndoe");
//        userRepository.save(user);
//
//        Medication med1 = new Medication();
//        med1.setName("Ibuprofeno");
//        med1.setDose(200);
//        med1.setHour("10");
//        med1.setTaken(false);
//        med1.setUser(user);
//        medicationRepository.save(med1);
//
//        Medication med2 = new Medication();
//        med2.setName("Paracetamol");
//        med2.setDose(500);
//        med2.setHour("12");
//        med2.setTaken(false);
//        med2.setUser(user);
//        medicationRepository.save(med2);
//
//        List<Medication> result = medicationRepository.findByUserId(user.getId());
//
//        assertThat(result).hasSize(2);
//        assertThat(result).extracting(Medication::getName)
//                .containsExactlyInAnyOrder("Ibuprofeno", "Paracetamol");
//    }
//
//    @Test
//    @DisplayName("findByUserIdOrderByNameAsc debería devolver medicamentos ordenados por nombre")
//    void testFindByUserIdOrderByNameAsc() {
//        User user = new User();
//        user.setUsername("janedoe");
//        userRepository.save(user);
//
//        Medication med1 = new Medication();
//        med1.setName("Zolpidem");
//        med1.setDose(10);
//        med1.setHour("20");
//        med1.setTaken(false);
//        med1.setUser(user);
//        medicationRepository.save(med1);
//
//        Medication med2 = new Medication();
//        med2.setName("Aspirina");
//        med2.setDose(100);
//        med2.setHour("08");
//        med2.setTaken(false);
//        med2.setUser(user);
//        medicationRepository.save(med2);
//
//        List<Medication> result = medicationRepository.findByUserIdOrderByNameAsc(user.getId());
//
//        assertThat(result).hasSize(2);
//        assertThat(result.get(0).getName()).isEqualTo("Aspirina");
//        assertThat(result.get(1).getName()).isEqualTo("Zolpidem");
//    }
//
//    @Test
//    @DisplayName("findByHourAndNotIsTaken debería devolver medicamentos no tomados en una hora específica")
//    void testFindByHourAndNotIsTaken() {
//        User user = new User();
//        user.setUsername("alex");
//        userRepository.save(user);
//
//        Medication med1 = new Medication();
//        med1.setName("Vitamina C");
//        med1.setDose(1000);
//        med1.setHour("09");
//        med1.setTaken(false);
//        med1.setUser(user);
//        medicationRepository.save(med1);
//
//        Medication med2 = new Medication();
//        med2.setName("Magnesio");
//        med2.setDose(400);
//        med2.setHour("09");
//        med2.setTaken(true);
//        med2.setUser(user);
//        medicationRepository.save(med2);
//
//        List<Medication> result = medicationRepository.findByHourAndNotIsTaken("09");
//
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0).getName()).isEqualTo("Vitamina C");
//    }
//}
