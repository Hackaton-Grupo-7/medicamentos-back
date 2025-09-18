package hackatongrupo7.medicamentos_grupo7.medication.dto;

public record MedicationResponseSummary(
        Long id,
        String name,
        int dose,
        boolean taken) {
}
