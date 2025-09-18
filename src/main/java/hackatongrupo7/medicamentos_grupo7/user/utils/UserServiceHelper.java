package hackatongrupo7.medicamentos_grupo7.user.utils;


import hackatongrupo7.medicamentos_grupo7.exceptions.NotFoundException;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.user.UserRepository;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequestAdmin;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceHelper {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User id"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User by username"));
    }

    public void validateUserDoesNotExist(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("already exists username");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("already exists email");
        }
    }

    public String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }


    public void updateUserData(UserRequest request, User user) {
        validateUserDoesNotExist(request.username(), request.email());

        String username = request.username() != null && !request.username().isEmpty()
                ? request.username() :
                user.getUsername();

        String email = request.email() != null && !request.email().isEmpty()
                ? request.email() :
                user.getEmail();

        String password = request.password() != null && !request.password().isEmpty()
                ? this.getEncodePassword(request.password()) :
                user.getPassword();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

    }

    public void updateUserAdminData(UserRequestAdmin request, User user) {
        validateUserDoesNotExist(request.username(), request.email());

        String username = request.username() != null && !request.username().isEmpty()
                ? request.username() :
                user.getUsername();

        String email = request.email() != null && !request.email().isEmpty()
                ? request.email() :
                user.getEmail();

        String password = request.password() != null && !request.password().isEmpty()
                ? this.getEncodePassword(request.password()) :
                user.getPassword();

        Role role = request.role() != null && !request.role().getRoleName().isEmpty()
                ? request.role() :
                user.getRole();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

    }
}
