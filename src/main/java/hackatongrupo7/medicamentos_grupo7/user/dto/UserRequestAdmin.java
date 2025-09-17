package hackatongrupo7.medicamentos_grupo7.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestAdmin(
        @NotBlank(message = "Username cannot be blank") @Size(max = 50, message = "Username cannot be longer than 50 characters!")
        String username,

        @NotBlank(message = "Name cannot be blank") @Size(max = 100, message = "Name cannot be longer than 100 characters!")
        String name,

        @NotBlank(message = "Email cannot be blank") @Email(message = "Email should be valid!")
        String email,

        @NotBlank(message = "Password cannot be blank") @Size(min = 8, message = "Password should be at least 8 characters long!")
        String password,

        @JsonDeserialize()
        @NotNull(message = "Role cannot be null")
        Role role) {
}
