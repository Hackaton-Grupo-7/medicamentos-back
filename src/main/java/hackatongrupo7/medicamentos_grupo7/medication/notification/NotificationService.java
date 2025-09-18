package hackatongrupo7.medicamentos_grupo7.medication.notification;

import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import hackatongrupo7.medicamentos_grupo7.medication.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MedicationRepository medicationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "0 * * * * *")
    public void checkAndSendReminders() {
        Time nowHour = Time.valueOf(LocalTime.now());
        List<Medication> dueMedications = medicationRepository.findByHourAndNotIsTaken(nowHour);

        for (Medication med : dueMedications) {
            Notification dto = Notification.builder()
                    .id(med.getId())
                    .name(med.getName())
                    .dose(med.getDose())
                    .hour(med.getHour())
                    .message("Is time to take " + med.getName() + " (" + med.getDose() + ")")
                    .createdAt(LocalDateTime.now())
                    .build();

            messagingTemplate.convertAndSend("/topic/medication-reminders", dto);
        }
    }
}
