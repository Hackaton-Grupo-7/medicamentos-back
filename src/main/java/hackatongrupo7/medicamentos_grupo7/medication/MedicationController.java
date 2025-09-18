package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationMapper;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationRequest;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseDetails;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseSummary;
import hackatongrupo7.medicamentos_grupo7.user.CustomUserDetails;
import hackatongrupo7.medicamentos_grupo7.utils.ApiMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;
    private final MedicationMapper medicationMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicationResponseDetails create(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody MedicationRequest medication) {
       return medicationService.saveMedication(medication, customUserDetails.getUser());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicationResponseSummary> getAll(
            @AuthenticationPrincipal CustomUserDetails customUserDetails ){
        return medicationService.findAllMedicationsByUser(customUserDetails.getUser());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicationResponseDetails getById(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id) {
        return medicationService.findById(id, customUserDetails.getUser());
    }

    @PutMapping("/{id}/taken")
    @ResponseStatus(HttpStatus.OK)
    public MedicationResponseSummary markAsTaken(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id) {
       return medicationService.markAsTaken(id, customUserDetails.getUser());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiMessageDto delete(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id) {
        medicationService.delete(id, customUserDetails.getUser() );
        return new ApiMessageDto("Delete successfully");
    }
}
