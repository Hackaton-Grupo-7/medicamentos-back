package hackatongrupo7.medicamentos_grupo7.medication.dto;

import hackatongrupo7.medicamentos_grupo7.medication.Medication;
import hackatongrupo7.medicamentos_grupo7.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

 @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "hour", source = "medicationRequest.hour")
    @Mapping(target = "name", source = "medicationRequest.name")
    @Mapping(target = "allergies", source = "medicationRequest.allergies")
    Medication toEntity(MedicationRequest medicationRequest, User user);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "dose", source = "dose")
    @Mapping(target = "taken", source = "taken")
    @Mapping(target = "allergies", source = "allergies")
    MedicationResponseSummary fromEntitySummary(Medication medication);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "allergies", source = "allergies")
    MedicationResponseDetails fromEntityDetails(Medication medication);

}
