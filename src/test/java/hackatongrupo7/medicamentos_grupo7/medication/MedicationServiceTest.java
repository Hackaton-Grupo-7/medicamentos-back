package hackatongrupo7.medicamentos_grupo7.medication;

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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationMapper medicationMapper;

    @Mock
    private EntityUtil entityUtil;

    @InjectMocks
    private MedicationService medicationService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
    }

    @Test
    void saveMedication_ShouldReturnResponseDetails() {
        MedicationRequest request = new MedicationRequest("Paracetamol", 500, "10", "Dolor de cabeza");
        Medication medication = new Medication();
        MedicationResponseDetails responseDetails = new MedicationResponseDetails(
                1L, "Paracetamol", 500, "10", false, true, "Dolor de cabeza", LocalDateTime.now()
        );

        when(medicationMapper.toEntity(request, user)).thenReturn(medication);
        when(medicationRepository.save(medication)).thenReturn(medication);
        when(medicationMapper.fromEntityDetails(medication)).thenReturn(responseDetails);

        MedicationResponseDetails result = medicationService.saveMedication(request, user);

        assertNotNull(result);
        assertEquals("Paracetamol", result.name());
        verify(medicationRepository).save(medication);
    }

    @Test
    void findMedicationSuggestions_ShouldReturnList() {
        Medication med = new Medication();
        List<Medication> meds = Arrays.asList(med);
        MedicationResponseSummary summary = new MedicationResponseSummary("Ibuprofeno", 400, true);

        when(medicationRepository.findByUserIdOrderByNameAsc(1L)).thenReturn(meds);
        when(entityUtil.mapEntitiesToDTOs(meds, medicationMapper::fromEntitySummary)).thenReturn(List.of(summary));

        List<MedicationResponseSummary> result = medicationService.findMedicationSuggestions();

        assertEquals(1, result.size());
        verify(medicationRepository).findByUserIdOrderByNameAsc(1L);
    }

    @Test
    void findMedicationSuggestions_ShouldThrowEmptyListException() {
        when(medicationRepository.findByUserIdOrderByNameAsc(1L)).thenReturn(Collections.emptyList());

        assertThrows(EmptyListException.class, () -> medicationService.findMedicationSuggestions());
    }

    @Test
    void findAllMedicationsByUser_ShouldReturnList() {
        Medication med = new Medication();
        List<Medication> meds = Arrays.asList(med);
        MedicationResponseSummary summary = new MedicationResponseSummary("Aspirina", 100, false);

        when(medicationRepository.findByUserId(user.getId())).thenReturn(meds);
        when(entityUtil.mapEntitiesToDTOs(meds, medicationMapper::fromEntitySummary)).thenReturn(List.of(summary));

        List<MedicationResponseSummary> result = medicationService.findAllMedicationsByUser(user);

        assertEquals(1, result.size());
        verify(medicationRepository).findByUserId(user.getId());
    }

    @Test
    void findAllMedicationsByUser_ShouldThrowEmptyListException() {
        when(medicationRepository.findByUserId(user.getId())).thenReturn(Collections.emptyList());

        assertThrows(EmptyListException.class, () -> medicationService.findAllMedicationsByUser(user));
    }

    @Test
    void findById_ShouldReturnDetails() {
        Medication med = new Medication();
        med.setId(1L);
        med.setUser(user);

        MedicationResponseDetails response = new MedicationResponseDetails(1L, "Paracetamol", 500, "10", false, true, "desc", LocalDateTime.now());

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));
        when(medicationMapper.fromEntityDetails(med)).thenReturn(response);

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
        Medication med = new Medication();
        User anotherUser = new User();
        anotherUser.setUsername("otherUser");
        med.setUser(anotherUser);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));

        assertThrows(UnauthorizedAccessException.class, () -> medicationService.findById(1L, user));
    }

    @Test
    void delete_ShouldDeleteMedication() {
        Medication med = new Medication();
        med.setUser(user);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));

        medicationService.delete(1L, user);

        verify(medicationRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowUnauthorizedAccessException() {
        Medication med = new Medication();
        User anotherUser = new User();
        anotherUser.setUsername("otherUser");
        med.setUser(anotherUser);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));

        assertThrows(UnauthorizedAccessException.class, () -> medicationService.delete(1L, user));
    }

    @Test
    void markAsTaken_ShouldMarkMedication() {
        Medication med = new Medication();
        med.setUser(user);
        med.setTaken(false);

        MedicationResponseSummary summary = new MedicationResponseSummary("Paracetamol", 500, true);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));
        when(medicationRepository.save(med)).thenReturn(med);
        when(medicationMapper.fromEntitySummary(med)).thenReturn(summary);

        MedicationResponseSummary result = medicationService.markAsTaken(1L, user);

        assertTrue(result.taken());
        verify(medicationRepository).save(med);
    }

    @Test
    void markAsTaken_ShouldThrowUnauthorizedAccessException() {
        Medication med = new Medication();
        User anotherUser = new User();
        anotherUser.setUsername("otherUser");
        med.setUser(anotherUser);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));

        assertThrows(UnauthorizedAccessException.class, () -> medicationService.markAsTaken(1L, user));
    }

    @Test
    void markAsTaken_ShouldReturnSummaryIfAlreadyTaken() {
        Medication med = new Medication();
        med.setUser(user);
        med.setTaken(true);

        MedicationResponseSummary summary = new MedicationResponseSummary("Paracetamol", 500, true);

        when(medicationRepository.findById(1L)).thenReturn(Optional.of(med));
        when(medicationMapper.fromEntitySummary(med)).thenReturn(summary);

        MedicationResponseSummary result = medicationService.markAsTaken(1L, user);

        assertTrue(result.taken());
        verify(medicationRepository, never()).save(any());
    }
}
