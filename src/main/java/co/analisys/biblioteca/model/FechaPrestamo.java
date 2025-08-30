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
@Schema(description = "Fecha en que se realizó el préstamo")
public class FechaPrestamo {
    @Schema(description = "Fecha del préstamo", example = "2023-12-15", format = "date")
    private LocalDate fechaprestamo_value;

    public FechaPrestamo() {
        this.fechaprestamo_value = LocalDate.now();
    }
}
