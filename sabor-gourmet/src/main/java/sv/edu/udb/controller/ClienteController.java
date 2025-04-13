package sv.edu.udb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.model.Cliente;
import sv.edu.udb.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con la gestión de clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Registrar un nuevo cliente")
    @ApiResponse(responseCode = "200", description = "Cliente registrado exitosamente",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Datos del cliente inválidos")
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody @Valid @Schema(description = "Datos del nuevo cliente") Cliente cliente) {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @Operation(summary = "Obtener la lista de todos los clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cliente.class))))
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @Operation(summary = "Obtener un cliente por su ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@Parameter(description = "ID del cliente a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar la información de un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Datos del cliente inválidos")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @Parameter(description = "ID del cliente a actualizar", example = "1") @PathVariable Long id,
            @RequestBody @Valid @Schema(description = "Nuevos datos del cliente") Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @Operation(summary = "Eliminar un cliente por su ID")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@Parameter(description = "ID del cliente a eliminar", example = "1") @PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}