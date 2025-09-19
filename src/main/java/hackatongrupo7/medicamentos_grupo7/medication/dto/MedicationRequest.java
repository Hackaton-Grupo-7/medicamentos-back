package hackatongrupo7.medicamentos_grupo7.medication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

public record MedicationRequest(

        @NotEmpty
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Positive
        int dose,

        Time hour,

        String description
) {
}
