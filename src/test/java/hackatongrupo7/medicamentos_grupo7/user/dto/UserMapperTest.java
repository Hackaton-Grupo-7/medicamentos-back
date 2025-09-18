package hackatongrupo7.medicamentos_grupo7.user.dto;

import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void shouldMapUserToUserResponse() {

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setName("Test Name");
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);

       
        UserResponse response = mapper.toResponse(user);

       
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("testuser", response.username());
        assertEquals("Test Name", response.name());
        assertEquals("test@email.com", response.email());
        assertEquals(Role.USER, response.role());
    }

    @Test
    void shouldMapUserRequestToEntity() {
        
        UserRequest request = new UserRequest(
                "testuser",
                "Test Name",
                "test@email.com",
                "plainPassword"
        );
        String encodedPassword = "encodedPassword";
        Role role = Role.USER;

        
        User user = mapper.toEntity(request, encodedPassword, role);

        
        assertNotNull(user);
        assertNull(user.getId()); 
        assertEquals("testuser", user.getUsername());
        assertEquals("Test Name", user.getName());
        assertEquals("test@email.com", user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void shouldMapUserRequestAdminToEntityAdmin() {
        
        UserRequestAdmin requestAdmin = new UserRequestAdmin(
                "adminuser",
                "Admin Name",
                "admin@email.com",
                "plainPassword",
                Role.ADMIN
        );
        String encodedPassword = "encodedPassword";

       
        User user = mapper.toEntityAdmin(requestAdmin, encodedPassword);

        
        assertNotNull(user);
        assertNull(user.getId());
        assertEquals("adminuser", user.getUsername());
        assertEquals("Admin Name", user.getName());
        assertEquals("admin@email.com", user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }
}
