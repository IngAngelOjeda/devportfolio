# CLAUDE.md — Portfolio Backend (Spring Boot)

## Visión general del proyecto
API REST para portfolio personal. Sirve datos estáticos/configurables
(sobre mí, experiencias, proyectos) y recibe mensajes del formulario de contacto.
No requiere autenticación en los endpoints públicos.

---

## Stack tecnológico
- **Java:** 21 (LTS)
- **Framework:** Spring Boot 3.4.x
- **Build:** Maven
- **Base de datos:** PostgreSQL (producción) / H2 en memoria (desarrollo)
- **ORM:** Spring Data JPA + Hibernate
- **Configuración:** `application.properties` exclusivamente (sin .yml)
- **Puerto:** 8080

---

## Estructura del proyecto
```
src/main/java/com/tuusuario/portfolio/
├── config/
│   ├── CorsConfig.java          # CORS para Angular
│   └── DataInitializer.java     # Carga datos iniciales (CommandLineRunner)
├── controller/
│   ├── SobreMiController.java
│   ├── ExperienciaController.java
│   ├── ProyectoController.java
│   └── ContactoController.java
├── service/
│   ├── SobreMiService.java
│   ├── ExperienciaService.java
│   ├── ProyectoService.java
│   └── ContactoService.java
├── repository/
│   ├── SobreMiRepository.java
│   ├── ExperienciaRepository.java
│   ├── ProyectoRepository.java
│   └── ContactoRepository.java
├── model/
│   ├── SobreMi.java
│   ├── Experiencia.java
│   ├── Proyecto.java
│   └── Contacto.java
├── dto/
│   ├── SobreMiDTO.java
│   ├── ExperienciaDTO.java
│   ├── ProyectoDTO.java
│   └── ContactoRequestDTO.java
└── PortfolioApplication.java
```

---

## Endpoints REST

### GET /api/sobre-mi
Retorna los datos personales del perfil.
```json
{
  "nombre": "Tu Nombre",
  "titulo": "Desarrollador Full Stack",
  "descripcion": "Párrafo sobre ti...",
  "tecnologias": ["Java", "Angular", "Spring Boot", "PostgreSQL"],
  "githubUrl": "https://github.com/tuusuario",
  "linkedinUrl": "https://linkedin.com/in/tuusuario",
  "email": "tu@email.com"
}
```

### GET /api/experiencias
Lista de experiencias laborales ordenadas por fecha descendente.
```json
[
  {
    "id": 1,
    "empresa": "Empresa S.A.",
    "cargo": "Desarrollador Backend",
    "fechaInicio": "2022-01",
    "fechaFin": "Presente",
    "descripcion": "Descripción del rol...",
    "tecnologias": ["Java", "Spring Boot", "PostgreSQL"]
  }
]
```

### GET /api/proyectos
Lista de proyectos. Los destacados (`destacado: true`) aparecen primero.
```json
[
  {
    "id": 1,
    "nombre": "e-Kuatia SIFEN",
    "descripcion": "Sistema de facturación electrónica...",
    "tecnologias": ["Java", "Spring Boot", "Angular"],
    "githubUrl": "https://github.com/...",
    "demoUrl": null,
    "destacado": true
  }
]
```

### POST /api/contacto
Recibe el formulario de contacto y persiste el mensaje.
Request body:
```json
{
  "nombre": "Nombre del visitante",
  "email": "visitante@email.com",
  "mensaje": "Hola, me gustaría contactarte..."
}
```
Response 201 Created:
```json
{ "mensaje": "Mensaje recibido. ¡Gracias por escribir!" }
```
Validaciones con Bean Validation (`@NotBlank`, `@Email`).

---

## Entidades JPA

### Experiencia.java
```java
@Entity
public class Experiencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empresa;
    private String cargo;
    private String fechaInicio;   // formato "YYYY-MM"
    private String fechaFin;      // "YYYY-MM" o "Presente"
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @ElementCollection
    private List<String> tecnologias;
    private int orden;
}
```

### Proyecto.java
```java
@Entity
public class Proyecto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @ElementCollection
    private List<String> tecnologias;
    private String githubUrl;
    private String demoUrl;
    private boolean destacado;
    private int orden;
}
```

### Contacto.java
```java
@Entity
public class Contacto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String mensaje;
    private LocalDateTime fechaRecibido;
    private boolean leido;
}
```

---

## application.properties

```properties
# Servidor
server.port=8080

# Base de datos desarrollo (H2)
spring.datasource.url=jdbc:h2:mem:portfoliodb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false

# Nombre de la app
spring.application.name=portfolio-backend
```

### application-prod.properties (perfil producción)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.h2.console.enabled=false
```

---

## CorsConfig.java
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
                "http://localhost:4200",   // Angular dev
                "https://tudominio.com"    // Producción
            )
            .allowedMethods("GET", "POST")
            .allowedHeaders("*");
    }
}
```

---

## Convenciones
- Kebab-case en properties: `spring.application.name`, nunca camelCase en keys
- DTOs separados de las entidades — nunca exponer la entidad directamente
- `@RestController` + `@RequestMapping("/api/...")` en todos los controllers
- Respuestas con `ResponseEntity<>` para control del status HTTP
- `@Valid` en los `@RequestBody` de POST
- Manejo de errores con `@ControllerAdvice` + `@ExceptionHandler`
- Sin Lombok (código explícito y legible)

---

## DataInitializer.java
Implementa `CommandLineRunner` para cargar datos de muestra al arrancar en dev.
Se activa solo con el perfil `dev` (`@Profile("dev")`).
Inserta: 1 registro SobreMi, 2-3 Experiencias, 3-4 Proyectos.

---

## Dependencias Maven (pom.xml)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

---

## Comandos útiles
```bash
# Arrancar en desarrollo
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Arrancar en producción
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

# Build
./mvnw clean package -DskipTests
```
