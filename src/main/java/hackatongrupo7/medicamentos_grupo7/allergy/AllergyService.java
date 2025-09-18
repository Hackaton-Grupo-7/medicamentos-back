package hackatongrupo7.medicamentos_grupo7.allergy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;

@Service
public class AllergyService {

    private final AllergyRepository repository;

    public AllergyService(AllergyRepository repository) {
        this.repository = repository;
    }

    public List<AllergyDTOResponse> getAllEntities() {
        List<AllergyDTOResponse> allergies = new ArrayList<>();

        repository.findAll().forEach(a -> {
            AllergyDTOResponse allergy = AllergyMapper.toDTO(a);
            allergies.add(allergy);
        });

        return allergies;
    }

    public AllergyDTOResponse storeEntity(AllergyDTORequest allergyDTORequest) {
        Allergy country = AllergyMapper.toEntity(allergyDTORequest);
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
