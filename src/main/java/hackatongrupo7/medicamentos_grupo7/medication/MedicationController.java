package hackatongrupo7.medicamentos_grupo7.medication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // Crear medicamento
    @PostMapping
    public ResponseEntity<Medication> create(@RequestBody Medication medication) {
        Medication saved = medicationService.save(medication);
        return ResponseEntity.ok(saved);
    }

    // Listar todos los medicamentos
    @GetMapping
    public ResponseEntity<List<Medication>> getAll() {
        List<Medication> meds = medicationService.findAll();
        return ResponseEntity.ok(meds);
    }

    // Obtener un medicamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medication> getById(@PathVariable Long id) {
        return medicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Marcar medicamento como tomado
    @PutMapping("/{id}/tomado")
    public ResponseEntity<Medication> markAsTaken(@PathVariable Long id) {
        Medication updated = medicationService.markAsTaken(id);
        return ResponseEntity.ok(updated);
    }

    // Eliminar medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
