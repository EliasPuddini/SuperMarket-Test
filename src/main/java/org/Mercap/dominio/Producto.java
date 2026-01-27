package org.Mercap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  private String descripcion;
  private double precioActual;


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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public double getPrecioActual() {
    return precioActual;
  }

  public void setPrecioActual(double precioActual) {
    this.precioActual = precioActual;
  }
}
