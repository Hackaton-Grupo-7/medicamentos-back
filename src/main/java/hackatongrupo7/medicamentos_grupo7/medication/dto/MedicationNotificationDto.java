package hackatongrupo7.medicamentos_grupo7.medication.dto;

import java.time.LocalDateTime;

public class MedicationNotificationDto {

    private Long id;
    private String name;
    private String dose;
    private String message;
    private String hour;
    private LocalDateTime createdAt;

    public MedicationNotificationDto() {}

    public MedicationNotificationDto(Long id, String name, String dose, String hour, String message, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.hour = hour;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getHour() { return hour; }
    public void setHour(String hour) { this.hour = hour; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
