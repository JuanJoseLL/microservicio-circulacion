package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estados posibles de un préstamo", enumAsRef = true)
public enum EstadoPrestamo {
    @Schema(description = "Préstamo activo, libro pendiente de devolución")
    ACTIVO, 
    
    @Schema(description = "Libro devuelto correctamente")
    DEVUELTO, 
    
    @Schema(description = "Préstamo vencido, devolución tardía")
    VENCIDO
}
