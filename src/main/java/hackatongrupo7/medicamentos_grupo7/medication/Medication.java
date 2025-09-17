package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "medication")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_pills", nullable = false, length = 100)
    @NotEmpty
    @Size(max = 100)
    private String nameOfPills;

    @Column(name = "pills_per_week", nullable = false)
    @Positive
    private Integer howMuchTakePerWeek;

    @Column(name = "last_taken", nullable = false)
    @NotNull
    private LocalDate lastTimeWhenTaked;

    @Column(name = "next_buy_date", nullable = false)
    @NotNull
    private LocalDate nextDateToBuy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
