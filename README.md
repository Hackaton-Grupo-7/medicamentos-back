# Medicamentos-Grupo7 💊

A Spring Boot web application for comprehensive medication management, enabling users to track medications, receive reminders, manage allergies, and maintain adherence.

## Purpose 🎯

- Track medication schedules, dosages, and intake
- Automated email and real-time notifications ⏰
- Secure user authentication with JWT
- Allergy profile management for safety

## Key Features 🛠️

- Full CRUD for medications and user profiles
- Authentication and authorization with Spring Security and JWT
- Scheduled reminders and WebSocket notifications
- Allergy alerts and management

## Architecture & Tech 🏗️

- Built on Spring Boot 3.5.5 and Java 21
- MySQL for production, H2 for testing
- JPA/Hibernate ORM and MapStruct for object mapping
- API documented with Springdoc OpenAPI (Swagger UI)
- JWT authentication with token expiration and blacklisting
- Configurable with application.properties and .env files
- Developer tools: Lombok, Spring DevTools, Cloudinary, SMTP email

## Project Structure & Architecture

This Spring Boot application follows a layered, modular architecture separating concerns for maintainability and scalability.
src/main/java/hackatongrupo7/medicamentos_grupo7/  
├── allergy/                          # Allergy Management Module  
│   ├── Allergy.java                  # JPA Entity  
│   ├── AllergyController.java        # REST Controller  
│   ├── AllergyMapper.java            # DTO Mapper  
│   ├── AllergyRepository.java        # Data Access Layer  
│   ├── AllergyService.java           # Business Logic  
│   └── dtos/  
│       ├── AllergyDTORequest.java    # Input DTO  
│       └── AllergyDTOResponse.java   # Output DTO  
├── medication/                       # Medication Management Module  
│   ├── Medication.java               # JPA Entity  
│   ├── MedicationController.java     # REST Controller  
│   ├── MedicationRepository.java     # Data Access Layer  
│   ├── MedicationService.java        # Business Logic  
│   ├── dto/  
│   │   ├── MedicationMapper.java     # MapStruct Mapper  
│   │   ├── MedicationRequest.java    # Input DTO  
│   │   ├── MedicationResponseDetails.java  
│   │   ├── MedicationResponseSummary.java  
│   │   └── MedicationResponseDefault.java  
│   └── notification/                 # Real-time Notifications  
│       ├── Notification.java         # Notification DTO  
│       └── NotificationService.java  # WebSocket Service  
├── config/                          # Configuration Classes  
│   ├── SchedulingConfig.java        # Task Scheduling  
│   └── WebSocketConfig.java         # WebSocket Configuration  
└── security/                        # Security Configuration  
└── config/  
└── SecurityConfig.java      # JWT & Authentication

## Getting Started 🚀

1. Clone the repository
2. Configure variables in `.env` or `application.properties`
3. Build and run with Maven or your IDE
4. Use the REST API to manage data and reminders

## Frontend and Backend Integration

The backend is designed to seamlessly integrate with the frontend application developed by our team using React. This separation allows independent development and deployment of both parts.

- The backend exposes RESTful APIs for managing medications, allergies, users, and reminders.
- Frontend consumes these APIs using asynchronous HTTP calls (e.g., fetch, axios).
- Authentication is handled securely via JWT tokens, which the frontend stores and sends in each API request.
- Real-time notifications are pushed from backend to frontend using WebSocket connections.
- Proper CORS and environment configuration ensure smooth communication between backend and frontend during development and production.

This decoupled architecture enhances maintainability, scalability, and user experience.

---

For the frontend project, see details and structure included in the [Frontend README](https://github.com/Hackaton-Grupo-7/front-hackaton/blob/main/README.md).

## Team and Credits 👥

This project was developed collaboratively by the following team members:

### Frontend Team 💻
- [@Aday25](https://github.com/Aday25)
- [@ValenMontilla7](https://github.com/ValenMontilla7)

### Backend Team 🛠️
| Digital Academy           | Valencia              |
|--------------------------|-----------------------|
| [@dmbiee](https://github.com/dmbiee)         | [@PCalvoGarcia](https://github.com/PCalvoGarcia)|
| [@EfrenTC](https://github.com/EfrenTC)    | [@sab-gif](https://github.com/sab-gif)    |

🙏 Special thanks to Fundación Somos F5 and Sanitas for organizing and supporting the 8th Hackathon.

---

Credits also go to all open-source projects and libraries used in this system. 📚

---