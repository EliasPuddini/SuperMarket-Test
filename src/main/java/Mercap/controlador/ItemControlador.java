package org.Mercap.controlador;

import org.Mercap.DTO.ItemDTO;
import org.Mercap.dominio.Item;
import org.Mercap.dominio.Producto;
import org.Mercap.servicios.ItemServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Items")
public class ItemControlador {

  @Autowired
  private ItemServicio itemServicio;

  @GetMapping
  public List<ItemDTO> getList(){
    return itemServicio.getItemsList();
  }

  @GetMapping("/{itemID}")
  public ItemDTO getById(@PathVariable("itemID")Long id){
    return itemServicio.getItem(id);
  }

  @PostMapping
  public void saveItem(@RequestBody Item item){
    itemServicio.saveItem(item);
  }

  @DeleteMapping("/{itemID}")
  public void deleteItem(@PathVariable("itemID") Long id){
    itemServicio.deleteItem(id);
  }

  @PatchMapping
  @PutMapping
  public void updateItem(@RequestBody Item item){
    this.itemServicio.updateItem(item);
  }
}
