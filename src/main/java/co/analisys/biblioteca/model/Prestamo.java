package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un préstamo de libro en el sistema")
public class Prestamo {
    @EmbeddedId
    @Schema(description = "Identificador único del préstamo", example = "PRES001")
    private PrestamoId id;

    @Embedded
    @Schema(description = "Identificador del usuario que realizó el préstamo", example = "USR001")
    private UsuarioId usuarioId;

    @Embedded
    @Schema(description = "Identificador del libro prestado", example = "LIB001")
    private LibroId libroId;

    @Embedded
    @Schema(description = "Fecha en que se realizó el préstamo")
    private FechaPrestamo fechaPrestamo;

    @Embedded
    @Schema(description = "Fecha prevista para la devolución del libro")
    private FechaDevolucionPrevista fechaDevolucionPrevista;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Estado actual del préstamo", example = "ACTIVO", 
            allowableValues = {"ACTIVO", "DEVUELTO", "VENCIDO"})
    private EstadoPrestamo estado;
}
