package org.Mercap.DTO;

import jakarta.persistence.OneToMany;
import org.Mercap.dominio.Item;
import org.Mercap.dominio.Venta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaDTO {
  private Long id;
  private LocalDateTime fecha;
  private List<ItemDTO> items;

  public VentaDTO(Venta venta) {
    this.id = id;
    this.fecha = venta.getFecha();

    List<ItemDTO> itemList = new ArrayList<>();
    venta.getItems().forEach(i ->{
      ItemDTO itemdto = new ItemDTO(i);
      itemList.add(itemdto);
    });

    this.items = itemList;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public List<ItemDTO> getItems() {
    return items;
  }

  public static List<VentaDTO> ventaDTOS(List<Venta> ventas){
    List<VentaDTO> ventaDTOs = new ArrayList<>();

    ventas.forEach(venta -> {
      VentaDTO ventaDTO = new VentaDTO(venta);
      ventaDTOs.add(ventaDTO);
    });
    return ventaDTOs;
  }
}
