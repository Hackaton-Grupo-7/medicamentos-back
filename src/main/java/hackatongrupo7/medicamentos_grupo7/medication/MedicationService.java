package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.exceptions.EmptyListException;
import hackatongrupo7.medicamentos_grupo7.exceptions.NotFoundException;
import hackatongrupo7.medicamentos_grupo7.exceptions.UnauthorizedAccessException;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationMapper;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationRequest;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseDetails;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseSummary;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.utils.EntityUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;
    private final EntityUtil mapperUtil;

         @Transactional
    public MedicationResponseDetails saveMedication(MedicationRequest medication, User user) {
        
        List<Medication> existing = medicationRepository.findByUserId(user.getId())
            .stream()
            .filter(m -> m.getName().equalsIgnoreCase(medication.name()))
            .toList();
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("Ya tienes este medicamento añadido.");
        }

        Medication medEntity = medicationMapper.toEntity(medication, user);

        if (user.getAllergies() != null && medEntity.getAllergies() != null) {
            for (String allergy : medEntity.getAllergies()) {
                if (user.getAllergies().contains(allergy)) {
                    throw new IllegalArgumentException("No puedes añadir este medicamento porque tienes alergia a él.");
                }
            }
        }

        Medication saveMedication = medicationRepository.save(medEntity);
        saveMedication.setActive(true);
        saveMedication.setCreatedAt(LocalDateTime.now());
        return medicationMapper.fromEntityDetails(saveMedication);
    }

    @Transactional
    public List<MedicationResponseSummary> findMedicationSuggestions(){
        List<Medication> medications = medicationRepository.findByUserIdOrderByNameAsc(1L);
        if (medications.isEmpty()){throw new EmptyListException();}
        return mapperUtil.mapEntitiesToDTOs(medications, medicationMapper::fromEntitySummary);
    }

    public List<MedicationResponseSummary> findAllMedicationsByUser(User user) {
        List<Medication> medications = medicationRepository.findByUserId(user.getId());

        if (medications.isEmpty()){throw  new EmptyListException();}

        return mapperUtil.mapEntitiesToDTOs(medications, medicationMapper::fromEntitySummary);
    }

    public MedicationResponseDetails findById(Long id, User user) {
        Medication medication =  medicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medication"));

        if (!medication.getUser().getUsername().equals(user.getUsername())) {
            throw new UnauthorizedAccessException();
        }

        return medicationMapper.fromEntityDetails(medication);

    }

    public void delete(Long id, User user) {
        Medication medication =  medicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medication"));

        if (!medication.getUser().getUsername().equals(user.getUsername())) {
            throw new UnauthorizedAccessException();
        }

        medicationRepository.deleteById(id);
    }

    public MedicationResponseSummary markAsTaken(Long id, User user) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medication"));

        if (!medication.getUser().getUsername().equals(user.getUsername())) {
            throw new UnauthorizedAccessException();
        }

        if (medication.isTaken()) {
            return medicationMapper.fromEntitySummary(medication);
        }


        medication.setTaken(true);
        Medication savedMedication = medicationRepository.save(medication);

        return medicationMapper.fromEntitySummary(savedMedication);
    }
}
