package hackatongrupo7.medicamentos_grupo7.allergy;

import hackatongrupo7.medicamentos_grupo7.user.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;

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

    @GetMapping("/my-user")
    @ResponseStatus(HttpStatus.OK)
    public List<AllergyDTOResponse> listAllMyAllergies(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return service.getAllAllergiesByUser(customUserDetails.getId());
    }

    @PostMapping("")
    public ResponseEntity<AllergyDTOResponse> postMethodName(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                             @RequestBody AllergyDTORequest dtoRequest) {
        if (dtoRequest.name().isBlank())
            return ResponseEntity.badRequest().build();

        AllergyDTOResponse entityStored = service.storeEntity(dtoRequest, customUserDetails.getUser());

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