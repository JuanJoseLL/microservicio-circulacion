package co.analisys.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Microservicio Circulacion - API",
        description = "API REST para la gestión de la circulación de libros en la biblioteca. " +
                     "Permite consultar información de libros, verificar disponibilidad, actualizar estado y realizar prestamos y devoluciones.",
        version = "1.0.0",
        contact = @Contact(
            name = "Equipo de Desarrollo",
            email = "desarrollo@analisys.co"
        )
    ),
    servers = {
        @Server(
            description = "Servidor de Desarrollo",
            url = "http://localhost:8083"
        ),
        @Server(
            description = "Servidor de Producción", 
            url = "https://api-circulacion.biblioteca.analisys.co"
        )
    },
    security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
    name = "Bearer Authentication",
    description = "Autenticación JWT Bearer Token requerida para acceder a los endpoints",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class OpenAPIConfig {
}