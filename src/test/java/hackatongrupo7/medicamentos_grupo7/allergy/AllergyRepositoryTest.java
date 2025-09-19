//package hackatongrupo7.medicamentos_grupo7.allergy;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class AllergyRepositoryTest {
//
//    @Autowired
//    private AllergyRepository allergyRepository;
//
//    @Test
//    @DisplayName("Guardar y recuperar una alergia")
//    void testSaveAndFindById() {
//        Allergy allergy = new Allergy();
//        allergy.setName("Peanuts");
//
//        Allergy saved = allergyRepository.save(allergy);
//
//        Optional<Allergy> found = allergyRepository.findById(saved.getId());
//        assertTrue(found.isPresent(), "La alergia debe encontrarse por ID");
//        assertEquals("Peanuts", found.get().getName(), "El nombre debe coincidir");
//    }
//
//    @Test
//    @DisplayName("Listar todas las alergias")
//    void testFindAll() {
//        Allergy allergy1 = new Allergy();
//        allergy1.setName("Peanuts");
//        Allergy allergy2 = new Allergy();
//        allergy2.setName("Pollen");
//
//        allergyRepository.save(allergy1);
//        allergyRepository.save(allergy2);
//
//        List<Allergy> allergies = allergyRepository.findAll();
//        assertEquals(2, allergies.size(), "Deben existir 2 alergias en la base de datos");
//    }
//
//    @Test
//    @DisplayName("Eliminar una alergia")
//    void testDelete() {
//        Allergy allergy = new Allergy();
//        allergy.setName("Dust");
//
//        Allergy saved = allergyRepository.save(allergy);
//        allergyRepository.deleteById(saved.getId());
//
//        Optional<Allergy> found = allergyRepository.findById(saved.getId());
//        assertFalse(found.isPresent(), "La alergia debe ser eliminada");
//    }
//}
