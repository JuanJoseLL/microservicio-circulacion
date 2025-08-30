package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.model.LibroId;
import co.analisys.biblioteca.model.Prestamo;
import co.analisys.biblioteca.model.PrestamoId;
import co.analisys.biblioteca.model.UsuarioId;
import co.analisys.biblioteca.service.CirculacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circulacion")
@Tag(name = "Circulación", description = "API para la gestión de préstamos y devoluciones de libros")
public class CirculacionController {
    @Autowired
    private CirculacionService circulacionService;

    @Operation(
        summary = "Registrar préstamo de un libro",
        description = "Permite a un bibliotecario registrar el préstamo de un libro a un usuario específico. " +
                     "Se valida la disponibilidad del libro y se crea un registro de préstamo con fecha de devolución prevista."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Préstamo registrado exitosamente"
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Datos de entrada inválidos o libro no disponible",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Token de autenticación requerido"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acceso denegado - Se requiere rol de bibliotecario"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    @PostMapping("/prestar")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ResponseEntity<Void> prestarLibro(
        @Parameter(
            description = "ID único del usuario que solicita el préstamo",
            required = true,
            example = "USR001"
        )
        @RequestParam String usuarioId,
        
        @Parameter(
            description = "ID único del libro a prestar",
            required = true,
            example = "LIB001"
        )
        @RequestParam String libroId
    ) {
        circulacionService.prestarLibro(new UsuarioId(usuarioId), new LibroId(libroId));
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Registrar devolución de un libro",
        description = "Permite registrar la devolución de un libro prestado. " +
                     "Puede ser ejecutado por bibliotecarios o por el usuario que realizó el préstamo."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Devolución registrada exitosamente"
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "ID de préstamo inválido o préstamo no encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Token de autenticación requerido"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acceso denegado - Se requiere rol de bibliotecario o ser el usuario del préstamo"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    @PostMapping("/devolver")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_USER')")
    public ResponseEntity<Void> devolverLibro(
        @Parameter(
            description = "ID único del préstamo a devolver",
            required = true,
            example = "PRES001"
        )
        @RequestParam String prestamoId
    ) {
        circulacionService.devolverLibro(new PrestamoId(prestamoId));
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Obtener todos los préstamos",
        description = "Recupera la lista completa de todos los préstamos registrados en el sistema, " +
                     "incluyendo préstamos activos, devueltos y vencidos. Solo accesible para bibliotecarios."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de préstamos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Prestamo.class))
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Token de autenticación requerido"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acceso denegado - Se requiere rol de bibliotecario"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    @GetMapping("/prestamos")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ResponseEntity<List<Prestamo>> obtenerTodosPrestamos() {
        List<Prestamo> prestamos = circulacionService.obtenerTodosPrestamos();
        return ResponseEntity.ok(prestamos);
    }
}
