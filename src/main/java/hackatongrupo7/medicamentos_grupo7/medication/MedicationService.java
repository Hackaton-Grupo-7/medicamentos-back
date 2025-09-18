package hackatongrupo7.medicamentos_grupo7.medication;

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
    private final EntityManager entityManager;

    @Transactional
    public MedicationResponseDetails saveMedication(MedicationRequest request, User user) {
        try {
            Medication medication = new Medication();
            medication.setName(request.name());
            medication.setDose(request.dose());
            medication.setHour(request.hour());
            medication.setDescription(request.description());
            medication.setActive(true);
            medication.setTaken(false);
            medication.setCreatedAt(LocalDateTime.now());

            User managedUser = entityManager.find(User.class, user.getId());
            medication.setUser(managedUser);

            medication.setId(null);

            Medication saved = medicationRepository.save(medication);

            return medicationMapper.fromEntityDetails(saved);

        } catch (Exception e) {
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Could not save medication", e);
        }
    }

    public List<MedicationResponseSummary> findAllMedicationsByUser(User user) {
        List<Medication> medications = medicationRepository.findByUserId(user.getId());

        if (medications.isEmpty()){throw  new RuntimeException("Empty list");}

        return mapperUtil.mapEntitiesToDTOs(medications, medicationMapper::fromEntitySummary);
    }

    public MedicationResponseDetails findById(Long id, User user) {
        Medication medication =  medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));

        if (medication.getUser().getId() != user.getId()){
            throw new RuntimeException("Unauthorized");
        };

        return medicationMapper.fromEntityDetails(medication);

    }

    public void delete(Long id, User user) {
        Medication medication =  medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));

        if (medication.getUser().getUsername() != user.getUsername()){
            throw new RuntimeException("Unauthorized");
        };

        medicationRepository.deleteById(id);
    }

    public MedicationResponseSummary markAsTaken(Long id, User user) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        if (!medication.getUser().getUsername().equals(user.getUsername())) {
            throw new RuntimeException("Unauthorized");
        }

        if (medication.isTaken()) {
            return medicationMapper.fromEntitySummary(medication);
        }


        medication.setTaken(true);
        Medication savedMedication = medicationRepository.save(medication);

        return medicationMapper.fromEntitySummary(savedMedication);
    }
}
