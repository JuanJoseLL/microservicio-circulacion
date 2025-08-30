package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@Schema(description = "Fecha prevista para la devolución del libro")
public class FechaDevolucionPrevista {
    @Schema(description = "Fecha límite para devolver el libro", example = "2023-12-29", format = "date")
    private LocalDate fechadevolucionprevista_value;

    public FechaDevolucionPrevista() {
        this.fechadevolucionprevista_value = LocalDate.now().plusDays(14); // 2 semanas por defecto
    }
}
