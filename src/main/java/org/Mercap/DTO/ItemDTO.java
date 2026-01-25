package org.Mercap.DTO;

import org.Mercap.dominio.Item;

public class ItemDTO {
  private Long id;
  private ProductoDTO producto;
  private Double precio;
  private int cantidad;

  public ItemDTO(Item item) {
    this.id = item.getId();
    this.producto = new ProductoDTO(item.getProducto());
    this.precio = item.getPrecio();
    this.cantidad = item.getCantidad();
  }

  public Long getId() {
    return id;
  }

  public ProductoDTO getProducto() {
    return producto;
  }

  public Double getPrecio() {
    return precio;
  }

  public int getCantidad() {
    return cantidad;
  }

}
