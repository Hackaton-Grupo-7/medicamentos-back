package hackatongrupo7.medicamentos_grupo7.medication.dto;

import java.time.LocalDateTime;

public record MedicationResponseDetails(
        Long id,
        String name,
        int dose,
        String hour,
        boolean taken,
        boolean active,
        String description,
        LocalDateTime createdAt

) {
}
