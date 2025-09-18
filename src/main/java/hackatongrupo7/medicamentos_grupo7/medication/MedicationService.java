package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationMapper;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationRequest;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseDetails;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseSummary;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.utils.EntityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Medication saveMedication =  medicationRepository.save(medicationMapper.toEntity(medication, user));
        System.out.println(saveMedication.isActive());
        System.out.println(saveMedication.isTaken());
        return medicationMapper.fromEntityDetails(saveMedication);
    }

    public List<MedicationResponseSummary> findAllMedicationsByUser(User user) {
        List<Medication> medications = medicationRepository.findByUserId(user.getId());

        if (medications.isEmpty()){throw  new RuntimeException("Empty list");}

        return mapperUtil.mapEntitiesToDTOs(medications, medicationMapper::fromEntitySummary);
    }

    public MedicationResponseDetails findById(Long id, User user) {
        Medication medication =  medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));

        if (medication.getUser().getUsername() != user.getUsername()){
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

        if (medication.getUser().getUsername() != user.getUsername()){
            throw new RuntimeException("Unauthorized");
        };

        medication.setTaken(true);
        return medicationMapper.fromEntitySummary(medicationRepository.save(medication));
    }
}
