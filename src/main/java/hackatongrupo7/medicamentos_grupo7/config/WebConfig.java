package hackatongrupo7.medicamentos_grupo7.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas del backend
                .allowedOrigins("https://front-hackaton-grcl.vercel.app/")
                .allowedHeaders("*")
                .allowCredentials(true); // permite enviar cookies o headers de autenticación
    }
}
