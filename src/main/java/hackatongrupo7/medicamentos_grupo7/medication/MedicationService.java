package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.Notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository,
                             SimpMessagingTemplate messagingTemplate) {
        this.medicationRepository = medicationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Medication save(Medication medication) {
        return medicationRepository.save(medication);
    }

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public Optional<Medication> findById(Long id) {
        return medicationRepository.findById(id);
    }

    public void delete(Long id) {
        medicationRepository.deleteById(id);
    }

    public Medication markAsTaken(Long id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
        medication.setTaken(true);
        return medicationRepository.save(medication);
    }

    @Scheduled(cron = "0 * * * * *")
    public void checkAndSendReminders() {
        String nowHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        List<Medication> dueMedications = medicationRepository.findByHourAndNotTaken(nowHour);

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
