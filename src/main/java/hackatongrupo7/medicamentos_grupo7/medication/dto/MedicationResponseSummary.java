package hackatongrupo7.medicamentos_grupo7.medication.dto;

import java.util.Set;

public record MedicationResponseSummary(
        Long id,
        String name,
        int dose,
        boolean taken) {
}
