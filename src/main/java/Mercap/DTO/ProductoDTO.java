package org.Mercap.DTO;

import org.Mercap.dominio.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDTO {
  private Long id;
  private String nombre;
  private String descripcion;
  private double precioActual;


  public ProductoDTO(Producto producto) {
    this.id = producto.getId();
    this.nombre = producto.getNombre();
    this.descripcion = producto.getDescripcion();
    this.precioActual = producto.getPrecioActual();
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public double getPrecioActual() {
    return precioActual;
  }

  public static List<ProductoDTO> ProductoDTO(List<Producto> productos) {

    List<ProductoDTO> productoDTOS = new ArrayList<>();
    productos.forEach(producto -> {
      ProductoDTO productoDTO = new ProductoDTO(producto);
      productoDTOS.add(productoDTO);
    });
    return  productoDTOS;
  }
}
