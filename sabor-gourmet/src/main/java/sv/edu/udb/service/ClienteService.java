package sv.edu.udb.service;

import jakarta.validation.Valid;
import sv.edu.udb.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente crearCliente(@Valid Cliente cliente); // Anotación @Valid en la interfaz
    List<Cliente> obtenerTodos();
    Cliente obtenerPorId(Long id);
    Cliente actualizarCliente(Long id, @Valid Cliente cliente); // Anotación @Valid en la interfaz
    void eliminarCliente(Long id);
}