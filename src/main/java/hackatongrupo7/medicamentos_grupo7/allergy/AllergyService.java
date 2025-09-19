package hackatongrupo7.medicamentos_grupo7.allergy;

import java.util.ArrayList;
import java.util.List;

import hackatongrupo7.medicamentos_grupo7.exceptions.EmptyListException;
import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import hackatongrupo7.medicamentos_grupo7.medication.MedicationRepository;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.utils.EntityUtil;
import org.springframework.stereotype.Service;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;

@Service
public class AllergyService {

    private final AllergyRepository repository;
    private final MedicationRepository medicationRepository;
    private final EntityUtil mapperUtil;

    public AllergyService(AllergyRepository repository, MedicationRepository medicationRepository, EntityUtil mapperUtil) {
        this.repository = repository;
        this.medicationRepository = medicationRepository;
        this.mapperUtil = mapperUtil;
    }


    public List<AllergyDTOResponse> getAllEntities() {
        List<AllergyDTOResponse> allergies = new ArrayList<>();

        repository.findAll().forEach(a -> {
            AllergyDTOResponse allergy = AllergyMapper.toDTO(a);
            allergies.add(allergy);
        });

        return allergies;
    }

    public List<AllergyDTOResponse> getAllAllergiesByUser(Long id) {
        List<Allergy> allergies = repository.findAllByUserId(id);

        if (allergies.isEmpty()){ throw new EmptyListException();}

        return mapperUtil.mapEntitiesToDTOs(allergies, AllergyMapper::toDTO);
    }

    public AllergyDTOResponse storeEntity(AllergyDTORequest allergyDTORequest, User user) {
        List<Medication> listMedicationByUser = medicationRepository.findByUserId(user.getId());
        List<Allergy> listMyAllergy = repository.findAllByUserId(user.getId());

        String allergyNameCleaned = allergyDTORequest.name().toLowerCase().trim();
        boolean hasConflict = listMedicationByUser.stream()
                .anyMatch(medication -> medication.getName().toLowerCase().trim().equals(allergyNameCleaned));

        if (hasConflict) {
            throw new RuntimeException("You have that medication added now");
        }

        boolean hasConflictAllergy = listMyAllergy.stream()
                .anyMatch(allergy -> allergy.getName().toLowerCase().trim().equals(allergyNameCleaned));

        if (hasConflict) {
            throw new RuntimeException("You already have that allergy added");
        }

        Allergy country = AllergyMapper.toEntity(allergyDTORequest,user );
        Allergy countryStored = repository.save(country);
        return AllergyMapper.toDTO(countryStored);
    }

    public AllergyDTOResponse showById(Long id) {
        Allergy country = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allergy not found"));
        return AllergyMapper.toDTO(country);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
