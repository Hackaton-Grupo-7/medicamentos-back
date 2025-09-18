package hackatongrupo7.medicamentos_grupo7.allergy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;

@ExtendWith(MockitoExtension.class)
public class AllergyServiceTest {

    @InjectMocks
    private AllergyService allergyService;

    @Mock
    private AllergyRepository allergyRepository;

    @BeforeEach
    void setUp() {
        allergyService = new AllergyService(allergyRepository);
    }

    @Test
    void testGetAllEntities() {

        List<Allergy> allergiesMock = List.of(
                new Allergy(1L, "Pollen"),
                new Allergy(2L, "Dust"));

        when(allergyRepository.findAll()).thenReturn(allergiesMock);
        List<AllergyDTOResponse> allergies = allergyService.getAllEntities();

        assertThat(allergies.size(), is(equalTo(2)));
        assertThat(allergies.get(0).name()).isEqualTo("Pollen");
        assertThat(allergies.get(1).name()).isEqualTo("Dust");
    }

    @Test
    void testStoreCountry_ShouldReturnCountryEntity() {
        AllergyDTORequest dust = new AllergyDTORequest("Dust");

        when(allergyRepository.save(Mockito.any(Allergy.class))).thenReturn(new Allergy(1L, dust.name()));
        AllergyDTOResponse storedEntity = allergyService.storeEntity(dust);

        assertThat(storedEntity.name(), is(equalTo("Dust")));
    }

    @Test
    void testDeleteById() {
        Long allergyId = 1L;

        allergyService.deleteById(allergyId);

        Mockito.verify(allergyRepository).deleteById(eq(allergyId));
    }

    @Test
    void testDeleteUser_Success() {
        Long allergyId = 1L;

        allergyService.deleteById(allergyId);

        verify(allergyRepository, times(1)).deleteById(allergyId);
        verify(allergyRepository, never()).existsById(any());
    }
}
