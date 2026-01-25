package org.Mercap.controlador;

import org.Mercap.DTO.SucursalDTO;
import org.Mercap.dominio.Sucursal;
import org.Mercap.servicios.SucursalServicio;
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
@RequestMapping("api/sucursal")
public class SucursalCotrolador {

  @Autowired
  private SucursalServicio sucursalServicio;

  @GetMapping
  public List<SucursalDTO> getList(){
    return this.sucursalServicio.getSucursalList();
  }

  @GetMapping("/{sucursalID}")
  public SucursalDTO getById(@PathVariable("sucursalID") Long id){
    return this.sucursalServicio.getSucursalById(id);
  }

  @PostMapping
  public void saveSucursal(@RequestBody Sucursal sucursal){
    this.sucursalServicio.saveSucursal(sucursal);
  }

  @DeleteMapping("/{sucursalID}")
  public void deleteSucursal(@PathVariable("sucursalID") Long id){
    this.sucursalServicio.deleteSucursalById(id);
  }


  @PutMapping
  public void updateSucursal(@RequestBody Sucursal sucursal){
    this.sucursalServicio.updateSucursal(sucursal);
  }

}
