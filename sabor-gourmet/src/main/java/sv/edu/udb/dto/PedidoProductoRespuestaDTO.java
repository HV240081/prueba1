package sv.edu.udb.dto;

import lombok.Data;

@Data
public class PedidoProductoRespuestaDTO {
    private Long id;
    private Long productoId;
    private String productoNombre; // Puedes incluir más detalles del producto si es necesario
    private Integer cantidad;
    private Double subtotal;
}