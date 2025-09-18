package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.user.User;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Positive
    private int dose;

    private Time hour;

    @NotNull
    private boolean taken = false;

    @NotNull
    private boolean active = true;

    private String description;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "medication_allergies", joinColumns = @JoinColumn(name = "medication_id"))
    @Column(name = "allergy")
    private Set<String> allergies;
}
    
