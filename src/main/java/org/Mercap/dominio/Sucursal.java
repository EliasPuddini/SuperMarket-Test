package org.Mercap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Sucursal {

  @Id
  private Long id;
  private String nombre;
  @OneToMany
  private List<Venta> ventas;
  @ManyToMany
  @JoinTable(
      name = "SucursalXProducto",
      joinColumns = @JoinColumn(name = "sucursal_id"),
      inverseJoinColumns = @JoinColumn(name = "producto_id")
  )
  private List<Producto> productos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Venta> getVentas() {
    return ventas;
  }

  public void setVentas(List<Venta> ventas) {
    this.ventas = ventas;
  }

  public List<Producto> getProductos() {
    return productos;
  }

  public void setProductos(List<Producto> productos) {
    this.productos = productos;
  }
}
