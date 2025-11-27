package hackatongrupo7.medicamentos_grupo7.config;

import hackatongrupo7.medicamentos_grupo7.models.User;
import hackatongrupo7.medicamentos_grupo7.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Crear usuario normal solo si no existe
            if (userRepository.count() == 0) {
                User user = new User();
                user.setEmail("user@sanimed.com");        // email del user
                user.setPassword(encoder.encode("user123")); // contraseña
                user.setRole("USER");                     // rol normal

                userRepository.save(user);

                System.out.println("Usuario normal creado correctamente!");
            }
        };
    }
}
