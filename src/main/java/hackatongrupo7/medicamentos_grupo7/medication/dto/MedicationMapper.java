package hackatongrupo7.medicamentos_grupo7.medication.dto;

import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import hackatongrupo7.medicamentos_grupo7.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "name", source = "medicationRequest.name")
    Medication toEntity(MedicationRequest medicationRequest, User user);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "dose", source = "dose")
    MedicationResponseSummary fromEntitySummary(Medication medication);

    @Mapping(target = "name", source = "name")
    MedicationResponseDetails fromEntityDetails(Medication medication);

}
