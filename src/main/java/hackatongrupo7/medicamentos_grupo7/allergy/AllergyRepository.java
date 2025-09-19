package hackatongrupo7.medicamentos_grupo7.allergy;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findAllByUserId(Long id);

}
