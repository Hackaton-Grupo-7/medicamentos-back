package hackatongrupo7.medicamentos_grupo7.user.dto;

import hackatongrupo7.medicamentos_grupo7.user.role.Role;

public record UserResponse(
        Long id,
        String username,
        String name,
        String email,
        Role role
) {
}
