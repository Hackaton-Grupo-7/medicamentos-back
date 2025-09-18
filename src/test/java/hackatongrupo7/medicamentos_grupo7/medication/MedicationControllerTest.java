package hackatongrupo7.medicamentos_grupo7.medication;

import com.fasterxml.jackson.databind.ObjectMapper;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationMapper;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationRequest;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseDetails;
import hackatongrupo7.medicamentos_grupo7.medication.dto.MedicationResponseSummary;
import hackatongrupo7.medicamentos_grupo7.user.CustomUserDetails;
import hackatongrupo7.medicamentos_grupo7.user.User;
import hackatongrupo7.medicamentos_grupo7.utils.ApiMessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; 

    @MockBean
    private MedicationService medicationService;

    @MockBean
    private MedicationMapper medicationMapper;

    private User user;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        customUserDetails = new CustomUserDetails(user);
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("POST /medications debería crear un medicamento")
    void testCreateMedication() throws Exception {
        MedicationRequest request = new MedicationRequest("Ibuprofeno", 200, "10", "Para el dolor");
        MedicationResponseDetails response = new MedicationResponseDetails(
                1L, "Ibuprofeno", 200, "10", false, true, "Para el dolor", LocalDateTime.now()
        );

        Mockito.when(medicationService.saveMedication(eq(request), any(User.class)))
                .thenReturn(response);

        mockMvc.perform(post("/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ibuprofeno"))
                .andExpect(jsonPath("$.dose").value(200));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("GET /medications/suggestions debería devolver sugerencias")
    void testGetSuggestions() throws Exception {
        MedicationResponseSummary summary = new MedicationResponseSummary("Aspirina", 100, false);

        Mockito.when(medicationService.findMedicationSuggestions())
                .thenReturn(List.of(summary));

        mockMvc.perform(get("/medications/suggestions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Aspirina"))
                .andExpect(jsonPath("$[0].dose").value(100));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("GET /medications debería devolver todos los medicamentos del usuario")
    void testGetAll() throws Exception {
        MedicationResponseSummary summary = new MedicationResponseSummary("Paracetamol", 500, false);

        Mockito.when(medicationService.findAllMedicationsByUser(any(User.class)))
                .thenReturn(List.of(summary));

        mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Paracetamol"))
                .andExpect(jsonPath("$[0].dose").value(500));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("GET /medications/{id} debería devolver un medicamento por id")
    void testGetById() throws Exception {
        MedicationResponseDetails response = new MedicationResponseDetails(
                1L, "Ibuprofeno", 200, "10", false, true, "Dolor", LocalDateTime.now()
        );

        Mockito.when(medicationService.findById(eq(1L), any(User.class)))
                .thenReturn(response);

        mockMvc.perform(get("/medications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ibuprofeno"))
                .andExpect(jsonPath("$.dose").value(200));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("PUT /medications/{id}/taken debería marcar como tomado")
    void testMarkAsTaken() throws Exception {
        MedicationResponseSummary summary = new MedicationResponseSummary("Ibuprofeno", 200, true);

        Mockito.when(medicationService.markAsTaken(eq(1L), any(User.class)))
                .thenReturn(summary);

        mockMvc.perform(put("/medications/1/taken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ibuprofeno"))
                .andExpect(jsonPath("$.taken").value(true));
    }

    @Test
    @WithMockUser(username = "testuser")
    @DisplayName("DELETE /medications/{id} debería eliminar un medicamento")
    void testDeleteMedication() throws Exception {
        mockMvc.perform(delete("/medications/1"))
                .andExpect(status().isNoContent());
    }
}
