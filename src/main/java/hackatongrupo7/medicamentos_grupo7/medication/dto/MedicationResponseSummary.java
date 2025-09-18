package hackatongrupo7.medicamentos_grupo7.medication.dto;

import java.util.Set;

public record MedicationResponseSummary(
        String name,
        int dose,
        boolean taken,
        Set<String> allergies) {
}
