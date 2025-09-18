package hackatongrupo7.medicamentos_grupo7.medication.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    private Long id;
    private String name;
    private int dose;
    private String message;
    private Time hour;
    private LocalDateTime createdAt;

}
