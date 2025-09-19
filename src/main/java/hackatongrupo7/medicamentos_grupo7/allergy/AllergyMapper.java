package hackatongrupo7.medicamentos_grupo7.allergy;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;
import hackatongrupo7.medicamentos_grupo7.user.User;

public class AllergyMapper {

    public static Allergy toEntity(AllergyDTORequest dtoRequest, User user) {
        Allergy allergy = new Allergy();
        allergy.setName(dtoRequest.name());
        allergy.setUser(user);

        return allergy;
    }

    public static AllergyDTOResponse toDTO(Allergy entity) {
        AllergyDTOResponse dtoResponse = new AllergyDTOResponse(entity.getId(), entity.getName());

        return dtoResponse;

    }
}
