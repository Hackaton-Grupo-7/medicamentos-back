package hackatongrupo7.medicamentos_grupo7.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;
    private GlobalExceptionHandlerTest exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandlerTest();
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    @RestController
    static class TestController {

        @GetMapping("/app-exception")
        public void appException() {
            throw new AppException("App exception occurred");
        }

        @GetMapping("/empty-list")
        public void emptyList() {
            throw new EmptyListException();
        }

        @GetMapping("/not-found")
        public void notFound() {
            throw new NotFoundException("User");
        }

        @GetMapping("/unauthorized")
        public void unauthorized() {
            throw new UnauthorizedAccessException();
        }

        @GetMapping("/invalid-format")
        public void invalidFormat() throws InvalidFormatException {
            throw new InvalidFormatException(null, "Invalid role value provided", null, String.class);
        }

        @GetMapping("/data-integrity")
        public void dataIntegrity() {
            throw new DataIntegrityViolationException("Constraint violation: duplicate key");
        }

        @GetMapping("/validation-error")
        public void validationError() throws MethodArgumentNotValidException {
            BindingResult bindingResult = Mockito.mock(BindingResult.class);
            FieldError error1 = new FieldError("user", "name", "must not be null");
            FieldError error2 = new FieldError("user", "email", "must be valid email");
            Mockito.when(bindingResult.getFieldErrors())
                    .thenReturn(java.util.Arrays.asList(error1, error2));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        @GetMapping("/single-validation-error")
        public void singleValidationError() throws MethodArgumentNotValidException {
            BindingResult bindingResult = Mockito.mock(BindingResult.class);
            FieldError error = new FieldError("user", "name", "must not be null");
            Mockito.when(bindingResult.getFieldErrors())
                    .thenReturn(Collections.singletonList(error));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
    }

    @Test
    void testAppExceptionHandler() throws Exception {
        mockMvc.perform(get("/app-exception"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("App exception occurred"));
    }

    @Test
    void testEmptyListExceptionHandler() throws Exception {
        mockMvc.perform(get("/empty-list"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("The list is empty")));
    }

    @Test
    void testNotFoundExceptionHandler() throws Exception {
        // NotFoundException extends AppException, so it should be handled by AppException handler
        mockMvc.perform(get("/not-found"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("User not found!!"));
    }

    @Test
    void testUnauthorizedAccessExceptionHandler() throws Exception {
        mockMvc.perform(get("/unauthorized"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("You are not authorized to access")));
    }

    @Test
    void testInvalidFormatExceptionHandler() throws Exception {
        mockMvc.perform(get("/invalid-format"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error in role:")))
                .andExpect(content().string(containsString("Invalid role value provided")));
    }

    @Test
    void testDataIntegrityViolationExceptionHandler() throws Exception {
        mockMvc.perform(get("/data-integrity"))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Error:")))
                .andExpect(content().string(containsString("Constraint violation: duplicate key")));
    }


    @Test
    void testAppExceptionWithCustomMessage() throws Exception {
        mockMvc.perform(get("/app-exception"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error").isString());
    }

    @Test
    void testEmptyListExceptionMessage() throws Exception {
        mockMvc.perform(get("/empty-list"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("The list is empty"));
    }

    @Test
    void testUnauthorizedAccessExceptionMessage() throws Exception {
        mockMvc.perform(get("/unauthorized"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("You are not authorized to access"));
    }

}