package org.Mercap.controlador;

import org.Mercap.DTO.SucursalDTO;
import org.Mercap.dominio.Sucursal;
import org.Mercap.servicios.SucursalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/sucursales")
public class SucursalCotrolador {

  @Autowired
  private SucursalServicio sucursalServicio;

  @GetMapping
  public ResponseEntity<?> getList(){

    try {
      List<SucursalDTO> sucursalDTOS =  this.sucursalServicio.getSucursalList();

      if(sucursalDTOS.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No se han encontrado sucursales. ");
      }

      return ResponseEntity.status(HttpStatus.OK).body(sucursalDTOS);
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al buscar las sucursales. ");
    }
  }

  @GetMapping("/{sucursalID}")
  public ResponseEntity<?> getById(@PathVariable("sucursalID") Long id){

    try {
      SucursalDTO sucursalDTO = this.sucursalServicio.getSucursalById(id);

      if (sucursalDTO == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No se ha encontrado la sucursal. ");
      }
      return ResponseEntity.status(HttpStatus.OK)
          .body(sucursalDTO);
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al buscar la sucursal. ");
    }
  }

  @PostMapping
  public ResponseEntity<?> saveSucursal(@RequestBody Sucursal sucursal){

    try{
      this.sucursalServicio.saveSucursal(sucursal);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Se ha creado la sucursal. ");
    } catch (Exception e){
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al crear la sucursal. ");
    }
  }

  @DeleteMapping("/{sucursalID}")
  public ResponseEntity<?> deleteSucursal(@PathVariable("sucursalID") Long id){

    try {
      if (!this.sucursalServicio.existSucursalByID(id)){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No se ha encontrado la sucursal. ");
      }

      this.sucursalServicio.deleteSucursalById(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body("Se ha eliminado la sucursal. ");
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al eliminar la sucursal. ");
    }
  }


  @PutMapping
  public ResponseEntity<?> updateSucursal(@RequestBody Sucursal sucursal){

    try {
      if (!this.sucursalServicio.existSucursalByID(sucursal.getId())){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No se ha encontrado la sucursal a editar.");
      }
      this.sucursalServicio.updateSucursal(sucursal);
      return ResponseEntity.status(HttpStatus.OK)
          .body("Se ha editado la sucursal. ");
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al editar la sucursal. ");
    }
  }
}