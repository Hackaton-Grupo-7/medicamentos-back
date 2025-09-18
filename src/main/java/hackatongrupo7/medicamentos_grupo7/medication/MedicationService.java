package hackatongrupo7.medicamentos_grupo7.medication;

import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationNotificationDto;
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

    // CRUD 
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

    // Marcar como tomado
    public Medication markAsTaken(Long id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
        medication.setTaken(true);
        return medicationRepository.save(medication);
    }

    // Scheduler: cada minuto revisa si hay medicaciones pendientes
    @Scheduled(cron = "0 * * * * *") // cada minuto en el segundo 0
    public void checkAndSendReminders() {
        String nowHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        List<Medication> dueMedications = medicationRepository.findByHourAndNotTaken(nowHour);

        for (Medication med : dueMedications) {
            MedicationNotificationDto dto = new MedicationNotificationDto(
                    med.getId(),
                    med.getName(),
                    med.getDose(),
                    med.getHour(),
                    "Es hora de tomar " + med.getName() + " (" + med.getDose() + ")",
                    LocalDateTime.now()
            );

            // Publicar en WebSocket
            messagingTemplate.convertAndSend("/topic/medication-reminders", dto);
        }
    }
}
