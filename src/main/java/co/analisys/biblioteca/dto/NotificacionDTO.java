package co.analisys.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para el envío de notificaciones a usuarios")
public class NotificacionDTO {
    @Schema(description = "Identificador del usuario destinatario", example = "USR001")
    private String usuarioId;
    
    @Schema(description = "Mensaje de notificación", example = "Su préstamo vence mañana")
    private String mensaje;
}
