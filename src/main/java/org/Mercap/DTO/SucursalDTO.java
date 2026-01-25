package org.Mercap.DTO;
import org.Mercap.dominio.Sucursal;
import java.util.List;

public class SucursalDTO {
  private Long id;
  private String nombre;
  private List<VentaDTO> ventas;
  private List<ProductoDTO> productos;
  public SucursalDTO(Sucursal sucursal) {
    this.id = sucursal.getId();
    this.nombre = sucursal.getNombre();
    List<VentaDTO> ventasDTO = VentaDTO.ventaDTOS(sucursal.getVentas());
    this.ventas = ventasDTO;
    List<ProductoDTO> productoDTOS = ProductoDTO.ProductoDTO(sucursal.getProductos());
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public List<VentaDTO> getVentas() {
    return ventas;
  }
}
