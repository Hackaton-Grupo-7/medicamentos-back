package hackatongrupo7.medicamentos_grupo7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MedicamentosGrupo7Application {

	public static void main(String[] args) {
		SpringApplication.run(MedicamentosGrupo7Application.class, args);
	}

}
