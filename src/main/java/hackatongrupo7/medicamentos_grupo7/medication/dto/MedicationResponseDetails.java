package hackatongrupo7.medicamentos_grupo7.medication.dto;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;

public record MedicationResponseDetails(
        Long id,
        String name,
        int dose,
        Time hour,
        boolean taken,
        boolean active,
        String description,
        LocalDateTime createdAt

) {
}
