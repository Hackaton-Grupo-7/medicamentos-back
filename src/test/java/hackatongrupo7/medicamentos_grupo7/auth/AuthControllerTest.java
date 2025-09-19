//package hackatongrupo7.medicamentos_grupo7.auth;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import hackatongrupo7.medicamentos_grupo7.auth.dto.LoginRequest;
//import hackatongrupo7.medicamentos_grupo7.auth.dto.RefreshRequest;
//import hackatongrupo7.medicamentos_grupo7.user.dto.UserRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void register_shouldReturnCreatedUser() throws Exception {
//        UserRequest userRequest = new UserRequest("testuser", "Test User", "testuser@mail.com", "password123");
//        mockMvc.perform(post("/api/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.username", is("testuser")))
//                .andExpect(jsonPath("$.email", is("testuser@mail.com")));
//    }
//
//    @Test
//    void login_shouldReturnAuthResponse() throws Exception {
//        UserRequest userRequest = new UserRequest("loginuser", "Login User", "loginuser@mail.com", "password123");
//        mockMvc.perform(post("/api/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userRequest)))
//                .andExpect(status().isCreated());
//
//        LoginRequest loginRequest = new LoginRequest("loginuser", "password123");
//        mockMvc.perform(post("/api/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.tokenType", is("Bearer")))
//                .andExpect(jsonPath("$.username", is("loginuser")))
//                .andExpect(jsonPath("$.token", not(emptyOrNullString())))
//                .andExpect(jsonPath("$.refreshToken", not(emptyOrNullString())));
//    }
//
//    @Test
//    void refresh_shouldReturnNewTokens() throws Exception {
//        UserRequest userRequest = new UserRequest("refreshuser", "Refresh User", "refreshuser@mail.com", "password123");
//        mockMvc.perform(post("/api/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userRequest)))
//                .andExpect(status().isCreated());
//
//        LoginRequest loginRequest = new LoginRequest("refreshuser", "password123");
//        String loginResponse = mockMvc.perform(post("/api/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        String refreshToken = objectMapper.readTree(loginResponse).get("refreshToken").asText();
//
//        RefreshRequest refreshRequest = new RefreshRequest(refreshToken);
//        mockMvc.perform(post("/api/auth/refresh")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(refreshRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.tokenType", is("Bearer")))
//                .andExpect(jsonPath("$.token", not(emptyOrNullString())))
//                .andExpect(jsonPath("$.refreshToken", not(emptyOrNullString())));
//    }
//
//    @Test
//    void logout_shouldReturnLogoutMessage() throws Exception {
//        UserRequest userRequest = new UserRequest("logoutuser", "Logout User", "logoutuser@mail.com", "password123");
//        mockMvc.perform(post("/api/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userRequest)))
//                .andExpect(status().isCreated());
//
//        LoginRequest loginRequest = new LoginRequest("logoutuser", "password123");
//        String loginResponse = mockMvc.perform(post("/api/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        String token = objectMapper.readTree(loginResponse).get("token").asText();
//
//        mockMvc.perform(post("/api/auth/logout")
//                .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message", containsString("Logout")));
//    }
//}