package hackatongrupo7.medicamentos_grupo7.user.dto;

import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(target = "password", source = "encodedPassword")
    User toEntity(UserRequest userRequest,  String encodedPassword, Role role);

    @Mapping(target = "password", source = "encodedPassword")
    User toEntityAdmin(UserRequestAdmin userRequestAdmin, String encodedPassword);

}
