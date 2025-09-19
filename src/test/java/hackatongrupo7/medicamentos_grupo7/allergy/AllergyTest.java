package hackatongrupo7.medicamentos_grupo7.allergy;

import hackatongrupo7.medicamentos_grupo7.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AllergyTest {

    @Test
    void testAllergyBuilderAndGetters() {
        Allergy allergy = Allergy.builder()
                .id(1L)
                .name("Peanuts")
                .build();

        assertEquals(1L, allergy.getId());
        assertEquals("Peanuts", allergy.getName());
    }

    @Test
    void testAllergyNoArgsConstructorAndSetters() {
        Allergy allergy = new Allergy();
        allergy.setId(2L);
        allergy.setName("Pollen");

        assertEquals(2L, allergy.getId());
        assertEquals("Pollen", allergy.getName());
    }

    @Test
    void testAllergyAllArgsConstructor() {
        Allergy allergy = new Allergy(3L, "Dust", new User());

        assertEquals(3L, allergy.getId());
        assertEquals("Dust", allergy.getName());
    }
}
