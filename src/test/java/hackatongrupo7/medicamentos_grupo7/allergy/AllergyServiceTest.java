package hackatongrupo7.medicamentos_grupo7.allergy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import hackatongrupo7.medicamentos_grupo7.exceptions.EmptyListException;
import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import hackatongrupo7.medicamentos_grupo7.medication.MedicationRepository;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.utils.EntityUtil;
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

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private EntityUtil mapperUtil;

    private User user;
    private AllergyDTORequest allergyRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        allergyRequest = new AllergyDTORequest("Pollen");
    }

    @Test
    void testGetAllEntities() {
        List<Allergy> allergiesMock = List.of(
                new Allergy(1L, "Pollen", new User()),
                new Allergy(2L, "Dust", new User()));

        when(allergyRepository.findAll()).thenReturn(allergiesMock);
        List<AllergyDTOResponse> allergies = allergyService.getAllEntities();

        assertThat(allergies.size(), is(equalTo(2)));
        assertThat(allergies.get(0).name()).isEqualTo("Pollen");
        assertThat(allergies.get(1).name()).isEqualTo("Dust");
    }


    @Test
    void testGetAllAllergiesByUser_ShouldThrowEmptyListException() {
        when(allergyRepository.findAllByUserId(1L)).thenReturn(Collections.emptyList());

        assertThrows(EmptyListException.class, () -> allergyService.getAllAllergiesByUser(1L));
    }

    @Test
    void testStoreEntity_ShouldReturnAllergyEntity_WhenNoConflicts() {
        Allergy savedAllergy = new Allergy(1L, "Pollen", user);
        AllergyDTOResponse expectedResponse = new AllergyDTOResponse(1L, "Pollen");

        when(medicationRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());
        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(Collections.emptyList());
        when(allergyRepository.save(any(Allergy.class))).thenReturn(savedAllergy);

        AllergyDTOResponse result = allergyService.storeEntity(allergyRequest, user);

        assertEquals("Pollen", result.name());
        verify(allergyRepository).save(any(Allergy.class));
        verify(medicationRepository).findByUserId(user.getId());
        verify(allergyRepository).findAllByUserId(user.getId());
    }

    @Test
    void testStoreEntity_ShouldThrowException_WhenMedicationConflictExists() {
        Medication existingMedication = new Medication();
        existingMedication.setName("pollen"); // lowercase to test case insensitive comparison
        existingMedication.setUser(user);

        when(medicationRepository.findByUserId(user.getId())).thenReturn(List.of(existingMedication));
        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> allergyService.storeEntity(allergyRequest, user)
        );

        assertEquals("Ya tienes esa medicacion añadida, no puedes añadirla como alergia", exception.getMessage());
        verify(allergyRepository, never()).save(any());
    }

    @Test
    void testStoreEntity_ShouldIgnoreCaseAndTrimSpaces_WhenCheckingMedications() {
        AllergyDTORequest requestWithSpaces = new AllergyDTORequest("  POLLEN  ");

        Medication existingMedication = new Medication();
        existingMedication.setName("pollen");
        existingMedication.setUser(user);

        when(medicationRepository.findByUserId(user.getId())).thenReturn(List.of(existingMedication));
        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> allergyService.storeEntity(requestWithSpaces, user)
        );

        assertEquals("Ya tienes esa medicacion añadida, no puedes añadirla como alergia", exception.getMessage());
    }

    @Test
    void testShowById_ShouldReturnAllergyDTOResponse() {
        Allergy allergy = new Allergy(1L, "Pollen", user);

        when(allergyRepository.findById(1L)).thenReturn(Optional.of(allergy));

        AllergyDTOResponse result = allergyService.showById(1L);

        assertEquals("Pollen", result.name());
        assertEquals(1L, result.id());
        verify(allergyRepository).findById(1L);
    }

    @Test
    void testShowById_ShouldThrowException_WhenAllergyNotFound() {
        when(allergyRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> allergyService.showById(1L)
        );

        assertEquals("Allergy not found", exception.getMessage());
    }

    @Test
    void testDeleteById() {
        Long allergyId = 1L;

        allergyService.deleteById(allergyId);

        verify(allergyRepository).deleteById(eq(allergyId));
    }

    @Test
    void testDeleteById_Success() {
        Long allergyId = 1L;

        allergyService.deleteById(allergyId);

        verify(allergyRepository, times(1)).deleteById(allergyId);
    }

    @Test
    void testStoreEntity_WithDifferentUser() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setUsername("anotherUser");

        Allergy savedAllergy = new Allergy(1L, "Pollen", anotherUser);

        when(medicationRepository.findByUserId(anotherUser.getId())).thenReturn(Collections.emptyList());
        when(allergyRepository.findAllByUserId(anotherUser.getId())).thenReturn(Collections.emptyList());
        when(allergyRepository.save(any(Allergy.class))).thenReturn(savedAllergy);

        AllergyDTOResponse result = allergyService.storeEntity(allergyRequest, anotherUser);

        assertEquals("Pollen", result.name());
        verify(medicationRepository).findByUserId(anotherUser.getId());
        verify(allergyRepository).findAllByUserId(anotherUser.getId());
    }
}