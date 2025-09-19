package hackatongrupo7.medicamentos_grupo7.allergy;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllergyMapperTest {

    @Test
    void testToEntity() {
        AllergyDTORequest dtoRequest = new AllergyDTORequest("Peanuts");

        Allergy allergy = AllergyMapper.toEntity(dtoRequest, );

        assertNotNull(allergy, "The mapped Allergy should not be null");
        assertEquals(dtoRequest.name(), allergy.getName(), "The name should match the DTO");
    }

    @Test
    void testToDTO() {
        Allergy allergy = new Allergy();
        allergy.setId(1L);
        allergy.setName("Pollen");

        AllergyDTOResponse dtoResponse = AllergyMapper.toDTO(allergy);

        assertNotNull(dtoResponse, "The mapped DTO should not be null");
        assertEquals(allergy.getId(), dtoResponse.id(), "The ID should match the entity");
        assertEquals(allergy.getName(), dtoResponse.name(), "The name should match the entity");
    }
}
