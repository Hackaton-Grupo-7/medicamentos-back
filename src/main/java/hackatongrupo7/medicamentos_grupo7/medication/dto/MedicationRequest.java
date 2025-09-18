package hackatongrupo7.medicamentos_grupo7.medication.dto;

import java.time.LocalDate;

public record MedicationRequest(
        String name,
        int dose,
        String hour,
        String description
) {
}
