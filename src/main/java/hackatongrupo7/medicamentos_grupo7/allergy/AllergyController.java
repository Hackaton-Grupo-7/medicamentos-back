package hackatongrupo7.medicamentos_grupo7.allergy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/allergies")
public class AllergyController {

    private final AllergyService service;

    public AllergyController(AllergyService service) {
        this.service = service;
    }

    @GetMapping
    public List<AllergyDTOResponse> listAllAllergies() {
        return service.getAllEntities();
    }

    @PostMapping("")
    public ResponseEntity<AllergyDTOResponse> postMethodName(@RequestBody AllergyDTORequest dtoRequest) {
        if (dtoRequest.name().isBlank())
            return ResponseEntity.badRequest().build();

        AllergyDTOResponse entityStored = service.storeEntity(dtoRequest);

        if (entityStored == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(entityStored);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}