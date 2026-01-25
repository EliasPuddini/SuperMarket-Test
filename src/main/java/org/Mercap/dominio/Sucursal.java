package org.Mercap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Sucursal {

  @Id
  private Long id;
  private String nombre;
  @OneToMany
  private List<Venta> ventas;
}
