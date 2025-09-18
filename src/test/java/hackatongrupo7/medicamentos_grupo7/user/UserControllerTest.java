package hackatongrupo7.medicamentos_grupo7.user;

import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequestAdmin;
import hackatongrupo7.medicamentos_grupo7.user.dto.UserResponse;
import hackatongrupo7.medicamentos_grupo7.user.role.Role;
import hackatongrupo7.medicamentos_grupo7.utils.ApiMessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Authentication auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();

        auth = new TestingAuthenticationToken("testuser", "password", "ROLE_USER");
    }

    @Test
    void testListAllUsers() throws Exception {
        UserResponse user1 = new UserResponse(1L, "user1", "Name1", "email1@test.com", Role.USER);
        UserResponse user2 = new UserResponse(2L, "user2", "Name2", "email2@test.com", Role.USER);

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/api/users").principal(auth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].username").value("user2"));
    }

    @Test
    void testGetUserById() throws Exception {
        UserResponse user = new UserResponse(1L, "user1", "Name1", "email1@test.com", Role.USER);
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1").principal(auth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("user1"));
    }

        @Test
    void testGetLoggedUser() throws Exception {
        UserResponse user = new UserResponse(1L, "user1", "Name1", "email1@test.com", Role.USER);
        when(userService.getLoggedUser(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/my-user").principal(auth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user1"));
    }

    @Test
    void testPutLoggedUser() throws Exception {
        UserRequest request = new UserRequest("user1", "Name1", "email@test.com", "password123");
        UserResponse updated = new UserResponse(1L, "user1", "Name1", "email@test.com", Role.USER);
        when(userService.updateLoggedUser(request, 1L)).thenReturn(updated);

        mockMvc.perform(put("/api/users/my-user")
                        .principal(auth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("email@test.com"));
    }

    @Test
    void testPutUserByAdmin() throws Exception {
        UserRequestAdmin requestAdmin = new UserRequestAdmin("adminUser", "Admin", "admin@test.com", "password123", Role.ADMIN);
        UserResponse updated = new UserResponse(2L, "adminUser", "Admin", "admin@test.com", Role.ADMIN);

        when(userService.updateUserByAdmin(requestAdmin, 2L)).thenReturn(updated);

        mockMvc.perform(put("/api/users/2")
                        .principal(auth)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestAdmin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }


    @Test
    void testDeleteLoggedUser() throws Exception {
        mockMvc.perform(delete("/api/users/my-user").principal(auth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Account deleted!!"));
    }

    @Test
    void testDeleteUserByAdmin() throws Exception {
        mockMvc.perform(delete("/api/users/2").principal(auth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Account deleted!!"));
    }
}
