package org.Mercap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SucursalXProducto {
  @Id
  private Long id;
  @ManyToOne
  private Producto producto;
  @ManyToOne
  private Sucursal sucursal;
}
