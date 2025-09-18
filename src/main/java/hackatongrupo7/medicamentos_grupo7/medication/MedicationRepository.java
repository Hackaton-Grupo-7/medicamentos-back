package hackatongrupo7.medicamentos_grupo7.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query("SELECT m FROM Medication m WHERE TIME_FORMAT(m.hour, '%H:%i') = TIME_FORMAT(:hour, '%H:%i') AND m.taken = false")
    List<Medication> findByHourAndNotIsTaken(@Param("hour") Time hour);

    List<Medication> findByUserId(Long id);

    List<Medication> findByUserIdOrderByNameAsc(Long Id);

}