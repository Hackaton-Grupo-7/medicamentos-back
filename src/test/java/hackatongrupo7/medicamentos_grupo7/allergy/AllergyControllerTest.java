package hackatongrupo7.medicamentos_grupo7.allergy;

import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTORequest;
import hackatongrupo7.medicamentos_grupo7.allergy.dtos.AllergyDTOResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AllergyController.class)
public class AllergyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AllergyService allergyService;

    @Autowired
    private ObjectMapper objectMapper;

    private AllergyDTORequest request;
    private AllergyDTOResponse response;

    @BeforeEach
    public void setUp() {
        request = new AllergyDTORequest("Peanuts");
        response = new AllergyDTOResponse(1L, "Peanuts");
    }

    @Test
    public void testListAllAllergies() throws Exception {
        Mockito.when(allergyService.getAllEntities()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/allergies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(response.id()))
                .andExpect(jsonPath("$[0].name").value(response.name()));
    }

    @Test
    public void testListAllAllergies_Empty() throws Exception {
        Mockito.when(allergyService.getAllEntities()).thenReturn(List.of());

        mockMvc.perform(get("/api/allergies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testPostAllergy_Success() throws Exception {
        Mockito.when(allergyService.storeEntity(any(AllergyDTORequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.name").value(response.name()));
    }

    @Test
    public void testPostAllergy_BadRequest_BlankName() throws Exception {
        AllergyDTORequest invalidRequest = new AllergyDTORequest("");

        mockMvc.perform(post("/api/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPostAllergy_NoContent() throws Exception {
        Mockito.when(allergyService.storeEntity(any(AllergyDTORequest.class))).thenReturn(null);

        mockMvc.perform(post("/api/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAllergy_Success() throws Exception {
        mockMvc.perform(delete("/api/allergies/{id}", 1L))
                .andExpect(status().isNoContent());

        Mockito.verify(allergyService).deleteById(1L);
    }

    @Test
    public void testDeleteAllergy_Exception() throws Exception {
        Mockito.doThrow(new RuntimeException("Error")).when(allergyService).deleteById(1L);

        mockMvc.perform(delete("/api/allergies/{id}", 1L))
                .andExpect(status().isInternalServerError());
    }
}
