package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.allergy.Allergy;
import hackatongrupo7.medicamentos_grupo7.allergy.AllergyRepository;
import hackatongrupo7.medicamentos_grupo7.exceptions.EmptyListException;
import hackatongrupo7.medicamentos_grupo7.exceptions.NotFoundException;
import hackatongrupo7.medicamentos_grupo7.exceptions.UnauthorizedAccessException;
import hackatongrupo7.medicamentos_grupo7.medication.dto.*;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.utils.EntityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationMapper medicationMapper;

    @Mock
    private EntityUtil mapperUtil;

    @Mock
    private AllergyRepository allergyRepository;

    @InjectMocks
    private MedicationService medicationService;

    private User user;
    private MedicationRequest medicationRequest;
    private Medication medication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        medicationRequest = new MedicationRequest("Paracetamol", 500, Time.valueOf(LocalTime.of(10, 0)), "Dolor de cabeza");
        medication = new Medication();
        medication.setId(1L);
        medication.setName("Paracetamol");
        medication.setUser(user);
    }

    @Test
    void saveMedication_ShouldReturnResponseDetails_WhenNoConflicts() {
        MedicationResponseDetails responseDetails = new MedicationResponseDetails(
                1L, "Paracetamol", 500, Time.valueOf(LocalTime.of(10, 0)), false, true, "Dolor de cabeza", LocalDateTime.now()
        );

        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(Collections.emptyList());
        when(medicationRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());
        when(medicationMapper.toEntity(medicationRequest, user)).thenReturn(medication);
        when(medicationRepository.save(any(Medication.class))).thenReturn(medication);
        when(medicationMapper.fromEntityDetails(any(Medication.class))).thenReturn(responseDetails);

        MedicationResponseDetails result = medicationService.saveMedication(medicationRequest, user);

        assertNotNull(result);
        assertEquals("Paracetamol", result.name());
        verify(medicationRepository).save(any(Medication.class));
        verify(allergyRepository).findAllByUserId(user.getId());
    }

    @Test
    void saveMedication_ShouldThrowException_WhenMedicationAlreadyExists() {
        Medication existingMedication = new Medication();
        existingMedication.setName("Paracetamol");
        existingMedication.setUser(user);

        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(Collections.emptyList());
        when(medicationRepository.findByUserId(user.getId())).thenReturn(List.of(existingMedication));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> medicationService.saveMedication(medicationRequest, user)
        );

        assertEquals("You already have this medication added.", exception.getMessage());
        verify(medicationRepository, never()).save(any());
    }

    @Test
    void saveMedication_ShouldThrowException_WhenAllergyConflictExists() {
        Allergy allergy = new Allergy();
        allergy.setName("paracetamol");
        allergy.setUser(user);

        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(List.of(allergy));
        when(medicationRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> medicationService.saveMedication(medicationRequest, user)
        );

        assertEquals("You have that allergy added now", exception.getMessage());
        verify(medicationRepository, never()).save(any());
    }

    @Test
    void saveMedication_ShouldIgnoreCaseAndTrimSpaces_WhenCheckingAllergies() {
        MedicationRequest requestWithSpaces = new MedicationRequest("  PARACETAMOL  ", 500, Time.valueOf(LocalTime.of(10, 0)), "Dolor de cabeza");

        Allergy allergy = new Allergy();
        allergy.setName("paracetamol");
        allergy.setUser(user);

        when(allergyRepository.findAllByUserId(user.getId())).thenReturn(List.of(allergy));
        when(medicationRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> medicationService.saveMedication(requestWithSpaces, user)
        );

        assertEquals("You have that allergy added now", exception.getMessage());
    }


    @Test
    void findMedicationSuggestions_ShouldThrowEmptyListException() {
        when(medicationRepository.findByUserIdOrderByNameAsc(1L)).thenReturn(Collections.emptyList());

        assertThrows(EmptyListException.class, () -> medicationService.findMedicationSuggestions());
    }


    @Test
    void findAllMedicationsByUser_ShouldThrowEmptyListException() {
        when(medicationRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());

        assertThrows(EmptyListException.class, () -> medicationService.findAllMedicationsByUser(user));
    }

    @Test
    void findById_ShouldReturnDetails() {
        MedicationResponseDetails response = new MedicationResponseDetails(
                1L, "Paracetamol", 500, Time.valueOf(LocalTime.of(10, 0)), false, true, "desc", LocalDateTime.now()
        );

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));
        when(medicationMapper.fromEntityDetails(medication)).thenReturn(response);

        MedicationResponseDetails result = medicationService.findById(1L, user);

        assertEquals("Paracetamol", result.name());
        verify(medicationRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowNotFoundException() {
        when(medicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> medicationService.findById(1L, user));
    }

    @Test
    void findById_ShouldThrowUnauthorizedAccessException() {
        User anotherUser = new User();
        anotherUser.setUsername("otherUser");
        medication.setUser(anotherUser);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

        assertThrows(UnauthorizedAccessException.class, () -> medicationService.findById(1L, user));
    }

    @Test
    void delete_ShouldDeleteMedication() {
        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

        medicationService.delete(1L, user);

        verify(medicationRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowNotFoundException_WhenMedicationNotFound() {
        when(medicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> medicationService.delete(1L, user));
        verify(medicationRepository, never()).deleteById(anyLong());
    }

    @Test
    void delete_ShouldThrowUnauthorizedAccessException() {
        User anotherUser = new User();
        anotherUser.setUsername("otherUser");
        medication.setUser(anotherUser);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

        assertThrows(UnauthorizedAccessException.class, () -> medicationService.delete(1L, user));
        verify(medicationRepository, never()).deleteById(anyLong());
    }

    @Test
    void markAsTaken_ShouldMarkMedication() {
        medication.setTaken(false);
        MedicationResponseSummary summary = new MedicationResponseSummary(1L,"Paracetamol", 500, true);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));
        when(medicationRepository.save(medication)).thenReturn(medication);
        when(medicationMapper.fromEntitySummary(medication)).thenReturn(summary);

        MedicationResponseSummary result = medicationService.markAsTaken(1L, user);

        assertTrue(result.taken());
        verify(medicationRepository).save(medication);
        assertTrue(medication.isTaken());
    }

    @Test
    void markAsTaken_ShouldThrowNotFoundException_WhenMedicationNotFound() {
        when(medicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> medicationService.markAsTaken(1L, user));
        verify(medicationRepository, never()).save(any());
    }

    @Test
    void markAsTaken_ShouldThrowUnauthorizedAccessException() {
        User anotherUser = new User();
        anotherUser.setUsername("otherUser");
        medication.setUser(anotherUser);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

        assertThrows(UnauthorizedAccessException.class, () -> medicationService.markAsTaken(1L, user));
        verify(medicationRepository, never()).save(any());
    }

    @Test
    void markAsTaken_ShouldReturnSummaryIfAlreadyTaken() {
        medication.setTaken(true);
        MedicationResponseSummary summary = new MedicationResponseSummary(1L,"Paracetamol", 500, true);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));
        when(medicationMapper.fromEntitySummary(medication)).thenReturn(summary);

        MedicationResponseSummary result = medicationService.markAsTaken(1L, user);

        assertTrue(result.taken());
        verify(medicationRepository, never()).save(any());
    }
}