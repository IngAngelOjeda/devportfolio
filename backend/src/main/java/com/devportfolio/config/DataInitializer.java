package com.devportfolio.config;

import com.devportfolio.model.Experiencia;
import com.devportfolio.model.Proyecto;
import com.devportfolio.model.SobreMi;
import com.devportfolio.repository.ExperienciaRepository;
import com.devportfolio.repository.ProyectoRepository;
import com.devportfolio.repository.SobreMiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SobreMiRepository sobreMiRepository;
    private final ExperienciaRepository experienciaRepository;
    private final ProyectoRepository proyectoRepository;

    public DataInitializer(SobreMiRepository sobreMiRepository,
                           ExperienciaRepository experienciaRepository,
                           ProyectoRepository proyectoRepository) {
        this.sobreMiRepository = sobreMiRepository;
        this.experienciaRepository = experienciaRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public void run(String... args) {
        if (sobreMiRepository.count() == 0) {
            cargarSobreMi();
            cargarExperiencias();
            cargarProyectos();
        }
    }

    private void cargarSobreMi() {
        SobreMi sm = new SobreMi();
        sm.setNombre("StackLab");
        sm.setTitulo("Convertimos ideas en productos digitales.");
        sm.setDescripcion(
                "En StackLab creemos que el software bien hecho cambia negocios. " +
                "Somos un equipo de desarrolladores apasionados por construir productos digitales rápidos, escalables y con impacto real.\n\n" +
                "No somos una agencia tradicional: trabajamos como parte de tu equipo, entendemos el problema antes de escribir la primera línea de código " +
                "y priorizamos entregar valor en cada iteración.\n\n" +
                "Desde MVPs que validan ideas en semanas hasta plataformas enterprise que soportan miles de usuarios, " +
                "nos adaptamos al ritmo y las necesidades de cada proyecto."
        );
        sm.setTecnologias(List.of("Java", "Spring Boot", "Angular", "React", "Node.js", "PostgreSQL", "Docker", "Flutter"));
        sm.setFotoUrl("/stackLab.png");
        sm.setGithubUrl("https://github.com/stacklab");
        sm.setEmail("stacklabcontacto@gmail.com");
        sobreMiRepository.save(sm);
    }

    private void cargarExperiencias() {
        Experiencia s1 = new Experiencia();
        s1.setEmpresa("Desarrollo a Medida");
        s1.setCargo("Aplicaciones Web & Mobile");
        s1.setFechaInicio("2022-01");
        s1.setFechaFin("Presente");
        s1.setDescripcion(
                "Diseñamos y desarrollamos aplicaciones web y mobile adaptadas 100% a las necesidades " +
                "de cada cliente. Desde MVPs hasta plataformas enterprise, acompañamos cada etapa " +
                "del ciclo de vida del producto: relevamiento, diseño, desarrollo, testing y despliegue."
        );
        s1.setTecnologias(List.of("Angular", "React", "Spring Boot", "Node.js", "PostgreSQL", "Docker"));
        s1.setOrden(1);

        Experiencia s2 = new Experiencia();
        s2.setEmpresa("Arquitectura Cloud");
        s2.setCargo("Infraestructura & DevOps");
        s2.setFechaInicio("2022-01");
        s2.setFechaFin("Presente");
        s2.setDescripcion(
                "Migramos y optimizamos infraestructuras hacia la nube con foco en escalabilidad, " +
                "seguridad y reducción de costos. Implementamos pipelines CI/CD, contenedores y " +
                "monitoreo para garantizar alta disponibilidad y entregas continuas."
        );
        s2.setTecnologias(List.of("Docker", "Kubernetes", "GitHub Actions", "Terraform", "Nginx"));
        s2.setOrden(2);

        Experiencia s3 = new Experiencia();
        s3.setEmpresa("Integración & APIs");
        s3.setCargo("Conectividad entre Sistemas");
        s3.setFechaInicio("2022-01");
        s3.setFechaFin("Presente");
        s3.setDescripcion(
                "Desarrollamos integraciones entre sistemas heterogéneos, APIs RESTful y conectores " +
                "con servicios de terceros como pasarelas de pago, facturación electrónica (SIFEN), " +
                "ERPs y plataformas de e-commerce. Garantizamos seguridad y trazabilidad en cada integración."
        );
        s3.setTecnologias(List.of("Spring Boot", "REST", "OAuth2", "SIFEN", "Webhooks", "RabbitMQ"));
        s3.setOrden(3);

        Experiencia s4 = new Experiencia();
        s4.setEmpresa("Consultoría Técnica");
        s4.setCargo("Auditoría & Code Review");
        s4.setFechaInicio("2023-01");
        s4.setFechaFin("Presente");
        s4.setDescripcion(
                "Analizamos el estado técnico de productos existentes, identificamos cuellos de botella " +
                "y proponemos planes de mejora accionables. Ofrecemos acompañamiento a equipos de desarrollo " +
                "con foco en buenas prácticas, rendimiento y mantenibilidad del código."
        );
        s4.setTecnologias(List.of("Java", "Spring Boot", "Angular", "SonarQube", "JMeter", "PostgreSQL"));
        s4.setOrden(4);

        experienciaRepository.saveAll(List.of(s1, s2, s3, s4));
    }

    private void cargarProyectos() {
        Proyecto p1 = new Proyecto();
        p1.setNombre("e-Kuatia SIFEN");
        p1.setDescripcion(
                "Sistema de facturación electrónica integrado con el SIFEN de Paraguay. " +
                "Genera, firma digitalmente y envía documentos tributarios electrónicos en formato XML " +
                "cumpliendo con la normativa vigente de la SET."
        );
        p1.setTecnologias(List.of("Java", "Spring Boot", "Angular", "PostgreSQL", "XML Digital Signature"));
        p1.setGithubUrl(null);
        p1.setDemoUrl(null);
        p1.setDestacado(true);
        p1.setOrden(1);

        Proyecto p2 = new Proyecto();
        p2.setNombre("Plataforma SaaS de Gestión");
        p2.setDescripcion(
                "Plataforma multi-tenant para gestión empresarial con módulos de facturación, " +
                "inventario, reportes en tiempo real y panel de administración. " +
                "Más de 50 empresas activas en producción."
        );
        p2.setTecnologias(List.of("Spring Boot", "Angular", "PostgreSQL", "Docker"));
        p2.setGithubUrl(null);
        p2.setDemoUrl(null);
        p2.setDestacado(true);
        p2.setOrden(2);

        Proyecto p3 = new Proyecto();
        p3.setNombre("App Mobile de Delivery");
        p3.setDescripcion(
                "Aplicación móvil para gestión de pedidos y seguimiento en tiempo real para " +
                "una cadena de restaurantes. Incluye panel para administradores, repartidores y clientes."
        );
        p3.setTecnologias(List.of("React Native", "Node.js", "PostgreSQL", "WebSockets", "Firebase"));
        p3.setGithubUrl(null);
        p3.setDemoUrl(null);
        p3.setDestacado(true);
        p3.setOrden(3);

        Proyecto p4 = new Proyecto();
        p4.setNombre("Portal de Recursos Humanos");
        p4.setDescripcion(
                "Sistema de gestión de RRHH con módulos de legajos digitales, liquidación de sueldos, " +
                "control de asistencia y portal de autoservicio para empleados."
        );
        p4.setTecnologias(List.of("Spring Boot", "Angular", "PostgreSQL", "JasperReports", "Docker"));
        p4.setGithubUrl(null);
        p4.setDemoUrl(null);
        p4.setDestacado(false);
        p4.setOrden(4);

        proyectoRepository.saveAll(List.of(p1, p2, p3, p4));
    }
}