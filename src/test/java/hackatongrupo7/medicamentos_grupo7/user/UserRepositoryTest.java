package hackatongrupo7.medicamentos_grupo7.user;

import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
       
        user = new User();
        user.setUsername("testuser");
        user.setName("Test User");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Test
    void testFindByUsername() {
        Optional<User> found = userRepository.findByUsername("testuser");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("testuser@example.com");
    }

    @Test
    void testFindByEmail() {
        Optional<User> found = userRepository.findByEmail("testuser@example.com");
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    void testExistsByUsername() {
        boolean exists = userRepository.existsByUsername("testuser");
        assertThat(exists).isTrue();

        boolean notExists = userRepository.existsByUsername("unknownuser");
        assertThat(notExists).isFalse();
    }

    @Test
    void testExistsByEmail() {
        boolean exists = userRepository.existsByEmail("testuser@example.com");
        assertThat(exists).isTrue();

        boolean notExists = userRepository.existsByEmail("unknown@example.com");
        assertThat(notExists).isFalse();
    }
}
