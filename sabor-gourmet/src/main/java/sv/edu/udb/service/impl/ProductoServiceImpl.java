package sv.edu.udb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.ProductoRepository;
import sv.edu.udb.service.ProductoService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated // Habilita la validación a nivel de la clase de servicio
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto producto) { // @Valid ELIMINADO
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) { // @Valid ELIMINADO
        Producto existente = obtenerPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setCategoria(producto.getCategoria());
        existente.setStock(producto.getStock());
        existente.setDisponible(producto.getDisponible());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}