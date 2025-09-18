package hackatongrupo7.medicamentos_grupo7.Notification;

import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    private Long id;
    private String name;
    private String dose;
    private String message;
    private String hour;
    private LocalDateTime createdAt;

}
