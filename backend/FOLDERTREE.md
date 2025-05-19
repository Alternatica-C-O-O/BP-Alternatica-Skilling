backend/
├── src/
│   ├── main/
│   │   ├── java/com/alternatica/emr/backend/
│   │   │   ├── controller/
│   │   │   │   ├── patient/
│   │   │   │   ├── appointment/
│   │   │   │   ├── medicalrecord/
│   │   │   │   ├── medication/
│   │   │   │   ├── auth/
│   │   │   │   └── ... (otros controladores)
│   │   │   ├── service/
│   │   │   │   ├── patient/
│   │   │   │   ├── appointment/
│   │   │   │   ├── medicalrecord/
│   │   │   │   ├── medication/
│   │   │   │   ├── auth/
│   │   │   │   └── ... (otros servicios)
│   │   │   ├── repository/
│   │   │   │   ├── patient/
│   │   │   │   ├── appointment/
│   │   │   │   ├── medicalrecord/
│   │   │   │   ├── medication/
│   │   │   │   ├── auth/
│   │   │   │   └── ... (otros repositorios)
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── OAuth2Config.java
│   │   │   │   ├── DatabaseConfig.java
│   │   │   │   └── ... (otras configuraciones)
│   │   │   ├── model/
│   │   │   │   ├── Patient.java
│   │   │   │   ├── Appointment.java
│   │   │   │   ├── MedicalRecord.java
│   │   │   │   ├── Medication.java
│   │   │   │   ├── User.java
│   │   │   │   ├── Role.java
│   │   │   │   └── ... (otras entidades/modelos)
│   │   │   ├── security/
│   │   │   │   ├── UserPrincipal.java
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   ├── ... (clases de seguridad personalizadas)
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── ... (otras excepciones personalizadas)
│   │   │   ├── util/
│   │   │   │   ├── ... (clases de utilidad)
│   │   │   └── EmrApplication.java (clase principal de Spring Boot)
│   │   └── resources/
│   │       ├── application.properties (o .yml)
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       ├── java/com/tuempresa/emr/
│       └── resources/
├── Dockerfile
├── pom.xml
├── README.md
└── .gitignore