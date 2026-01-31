package org.Mercap.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sucursal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @OneToMany(mappedBy = "sucursal")
  private List<Venta> ventas;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "SucursalXProducto",
      joinColumns = @JoinColumn(name = "sucursal_id"),
      inverseJoinColumns = @JoinColumn(name = "producto_id"),
      uniqueConstraints = {
          @UniqueConstraint(
              columnNames = {"sucursal_id", "producto_id"}
          )
      }
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

  @JsonProperty("productos")
  public List<Producto> getProductos() {
    return productos;
  }

  public void setProductos(List<Producto> productos) {
    this.productos = productos;
  }
}
