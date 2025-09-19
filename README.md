# Sanimed - Grupo 7

Aplicación web desarrollada con **Spring Boot** para la gestión integral de medicamentos. Permite a los usuarios llevar un control de sus tratamientos, recibir recordatorios en tiempo real, gestionar alergias y mantener la adherencia médica de forma segura.

---

## Objetivo 

- Gestionar horarios, dosis y toma de medicamentos
- Recordatorios automáticos vía email y notificaciones en tiempo real ⏰
- Autenticación segura de usuarios con JWT
- Gestión de alergias para evitar interacciones peligrosas

---

## Características principales 

- CRUD completo para medicamentos y perfiles de usuario
- Autenticación y autorización con **Spring Security + JWT**
- Recordatorios programados y notificaciones con **WebSockets**
- Alertas y gestión de alergias personalizadas
- Documentación de API con **Springdoc OpenAPI (Swagger UI)**
- Subida de imágenes con **Cloudinary**
- Envío de correos electrónicos con **SMTP**

---

## Arquitectura & Tecnologías 

- **Java 21** + **Spring Boot 3.5.5**
- **Base de datos**: MySQL 
- **ORM**: JPA / Hibernate
- **MapStruct** para mapeo de DTOs
- **JWT** con expiración y lista negra (blacklisting)
- **Notificaciones en tiempo real** con WebSockets
- **Plantillas** con Thymeleaf (emails y notificaciones)
- **Swagger UI** para documentación de API
- **Lombok** para reducción de boilerplate
- **Mockito & JUnit 5** para testing

---

## Estructura del Proyecto 📂

```
src/main/java/hackatongrupo7/medicamentos_grupo7/  
├── allergy/                          # Módulo de Alergias  
│   ├── Allergy.java                  # Entidad JPA  
│   ├── AllergyController.java        # Controlador REST  
│   ├── AllergyMapper.java            # Mapper DTO  
│   ├── AllergyRepository.java        # Repositorio JPA  
│   ├── AllergyService.java           # Lógica de negocio  
│   └── dtos/  
│       ├── AllergyDTORequest.java    # DTO de entrada  
│       └── AllergyDTOResponse.java   # DTO de salida  
├── medication/                       # Módulo de Medicamentos  
│   ├── Medication.java               # Entidad JPA  
│   ├── MedicationController.java     # Controlador REST  
│   ├── MedicationRepository.java     # Repositorio JPA  
│   ├── MedicationService.java        # Lógica de negocio  
│   ├── dto/  
│   │   ├── MedicationMapper.java     # Mapper MapStruct  
│   │   ├── MedicationRequest.java    # DTO de entrada  
│   │   ├── MedicationResponseDetails.java  
│   │   ├── MedicationResponseSummary.java  
│   │   └── MedicationResponseDefault.java  
│   └── notification/                 # Notificaciones en tiempo real  
│       ├── Notification.java         # DTO de notificación  
│       └── NotificationService.java  # Servicio WebSocket  
├── config/                          # Configuraciones  
│   ├── SchedulingConfig.java        # Configuración de tareas programadas  
│   └── WebSocketConfig.java         # Configuración WebSocket  
└── security/                        # Seguridad  
    └── SecurityConfig.java          # Configuración JWT & Spring Security
```

---

## Dependencias principales 📦

Según el `pom.xml`, el proyecto incluye:

- `spring-boot-starter-data-jpa` → JPA/Hibernate
- `spring-boot-starter-security` → Seguridad
- `spring-boot-starter-web` → API REST
- `spring-boot-starter-mail` → Envío de correos
- `spring-boot-starter-thymeleaf` → Plantillas de email
- `spring-boot-starter-websocket` → Notificaciones en tiempo real
- `springdoc-openapi-starter-webmvc-ui` → Documentación de API
- `com.cloudinary` → Gestión de imágenes
- `jjwt` (impl, api, jackson) → Autenticación JWT
- `lombok` → Simplificación de código
- `mapstruct` → Mapeo de DTOs
- `mockito` + `spring-security-test` → Pruebas unitarias e integración

---

## Instalación y configuración 🚀

### 1. Clonar repositorio
```bash
git clone https://github.com/Hackaton-Grupo-7/back-hackaton.git
cd back-hackaton
```

### 2. Configuración de variables
Editar `.env` o `application.properties` con:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/medicamentos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=claveSecretaSuperSegura
jwt.expiration=3600000
```

### 3. Compilar y ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

---

## Documentación de API 📖

Una vez iniciado el proyecto, la documentación de la API estará disponible en:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Pruebas ✅

Ejecutar todos los tests (JUnit + Mockito):
```bash
mvn test
```


---

## Despliegue en Producción 🌍

Generar el `.jar`:
```bash
mvn clean package
```

Ejecutar:
```bash
java -jar target/medicamentos-grupo7-0.0.1-SNAPSHOT.jar
```

Se recomienda:
- Usar **Docker** + **MySQL** para despliegue
- Configurar variables de entorno en el servidor
- Integrar con CI/CD (GitHub Actions, Jenkins, etc.)

---

## Integración con el Frontend 🎨

El frontend (React) consume la API de este backend mediante peticiones HTTP y gestiona la autenticación con JWT.

👉 Frontend: [Repositorio Frontend](https://github.com/Hackaton-Grupo-7/front-hackaton)

---

## Equipo 👥

### Frontend 💻
- [@Aday25](https://github.com/Aday25)
- [@ValenMontilla7](https://github.com/ValenMontilla7)

### Backend 🛠️
- [@dmbiee](https://github.com/dmbiee)
- [@PCalvoGarcia](https://github.com/PCalvoGarcia)
- [@EfrenTC](https://github.com/EfrenTC)
- [@sab-gif](https://github.com/sab-gif)

🙏 Agradecimientos a **Fundación Somos F5** y **Sanitas** por el apoyo en la 8ª Hackathon.

