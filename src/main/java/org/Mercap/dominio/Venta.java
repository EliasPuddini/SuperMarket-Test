package org.Mercap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Venta {
  @Id
  private Long id;
  private LocalDateTime fecha;
  @OneToMany
  private List<Item> items;
}
