package org.Mercap.servicios;

import org.Mercap.DTO.ItemDTO;
import org.Mercap.dominio.Item;
import org.Mercap.repositorios.ItemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServicio {

  @Autowired
  private ItemRepositorio itemRepositorio;

  public List<ItemDTO> getItemsList(){
    return this.itemRepositorio.findAll().stream().map(ItemDTO::new).collect(Collectors.toList());
  }

  public ItemDTO getItem(Long id){
    return this.itemRepositorio.findById(id).map(ItemDTO::new).orElse(null);
  }

  public void saveItem(Item item){
    this.itemRepositorio.save(item);
  }

  public void deleteItem(long id){
    this.itemRepositorio.deleteById(id);
  }

  public ItemDTO updateItem(Item itemActualizado) {
    return itemRepositorio.findById(itemActualizado.getId()).map(item -> {

      item.setProducto(itemActualizado.getProducto());
      item.setPrecio(itemActualizado.getPrecio());
      item.setCantidad(itemActualizado.getCantidad());

      itemRepositorio.save(item);
      return new ItemDTO(item);

    }).orElse(null);
  }

}
