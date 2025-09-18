package hackatongrupo7.medicamentos_grupo7.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SwaggerConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testOpenAPIBeanExists() {
        SwaggerConfig swaggerConfig = applicationContext.getBean(SwaggerConfig.class);
        assertNotNull(swaggerConfig, "SwaggerConfig bean should be loaded");

        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertNotNull(openAPI, "OpenAPI bean should not be null");

        assertNotNull(openAPI.getInfo(), "OpenAPI info should not be null");
        assertEquals("Hackathon API", openAPI.getInfo().getTitle());
        assertEquals("1.0.0", openAPI.getInfo().getVersion());

        assertNotNull(openAPI.getComponents(), "Components should not be null");
        assertTrue(openAPI.getComponents().getSecuritySchemes().containsKey("bearerAuth"),
                "Security scheme 'bearerAuth' should exist");
        assertTrue(openAPI.getComponents().getSchemas().containsKey("ErrorResponse"),
                "Schema 'ErrorResponse' should exist");
        assertTrue(openAPI.getComponents().getResponses().containsKey("NoContent"),
                "Response 'NoContent' should exist");
    }
}
