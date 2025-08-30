package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Identificador único del préstamo")
public class PrestamoId implements Serializable {
    @Schema(description = "Valor del identificador del préstamo", example = "PRES001")
    private String prestamoid_value;
}
