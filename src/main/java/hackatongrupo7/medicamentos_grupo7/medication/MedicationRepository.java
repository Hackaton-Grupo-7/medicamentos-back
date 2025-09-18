package hackatongrupo7.medicamentos_grupo7.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query("SELECT m FROM Medication m WHERE m.hour = :hour AND m.taken = false")
    List<Medication> findByHourAndNotTaken(@Param("hour") String hour);

}