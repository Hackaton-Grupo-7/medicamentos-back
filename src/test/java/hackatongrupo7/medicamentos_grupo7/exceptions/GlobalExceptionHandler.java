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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    static class TestController {
        @org.springframework.web.bind.annotation.GetMapping("/app-exception")
        public void appException() {
            throw new AppException("App exception occurred");
        }

        @org.springframework.web.bind.annotation.GetMapping("/empty-list")
        public void emptyList() {
            throw new EmptyListException();
        }

        @org.springframework.web.bind.annotation.GetMapping("/unauthorized")
        public void unauthorized() {
            throw new UnauthorizedAccessException();
        }

        @org.springframework.web.bind.annotation.GetMapping("/invalid-format")
        public void invalidFormat() throws InvalidFormatException {
            throw new InvalidFormatException(null, "Invalid value", null, String.class);
        }

        @org.springframework.web.bind.annotation.GetMapping("/data-integrity")
        public void dataIntegrity() {
            throw new DataIntegrityViolationException("Constraint violation");
        }

        @org.springframework.web.bind.annotation.GetMapping("/validation-error")
        public void validationError() throws MethodArgumentNotValidException {
            BindingResult bindingResult = Mockito.mock(BindingResult.class);
            FieldError error = new FieldError("objectName", "field", "must not be null");
            Mockito.when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(error));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
    }

    @Test
    void testAppExceptionHandler() throws Exception {
        mockMvc.perform(get("/app-exception"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("App exception occurred")));
    }

    @Test
    void testEmptyListHandler() throws Exception {
        mockMvc.perform(get("/empty-list"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUnauthorizedHandler() throws Exception {
        mockMvc.perform(get("/unauthorized"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("You are not authorized")));
    }

    @Test
    void testInvalidFormatHandler() throws Exception {
        mockMvc.perform(get("/invalid-format"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid value")));
    }

    @Test
    void testDataIntegrityHandler() throws Exception {
        mockMvc.perform(get("/data-integrity"))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Constraint violation")));
    }

    @Test
    void testValidationErrorHandler() throws Exception {
        mockMvc.perform(get("/validation-error"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("must not be null")));
    }
}
