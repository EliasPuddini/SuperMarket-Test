package org.Mercap.controlador;

import org.Mercap.DTO.ProductoDTO;
import org.Mercap.DTO.SucursalDTO;
import org.Mercap.DTO.VentaDTO;
import org.Mercap.dominio.Item;
import org.Mercap.dominio.Producto;
import org.Mercap.dominio.Sucursal;
import org.Mercap.dominio.Venta;
import org.Mercap.servicios.SucursalServicio;
import org.Mercap.servicios.VentaServicio;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/ventas")
public class VentaControlador {

  @Autowired
  private VentaServicio ventaServicio;
  @Autowired
  private SucursalServicio sucursalServicio;

  @GetMapping
  public ResponseEntity<?> getList(){

    try{
      List<VentaDTO> ventas = ventaServicio.getVentaList();
      return ResponseEntity.ok(ventas);
    }catch (Exception e){
      return ResponseEntity.
            status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al obtener la lista de ventas");
    }

  }

  @GetMapping("/{ventaID}")
  public ResponseEntity<?> getById(@PathVariable("ventaID")Long id){

    try {
      VentaDTO ventaDTO = this.ventaServicio.getVentaById(id);
      return ResponseEntity.ok(ventaDTO);
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al obtener la venta de Id" + id);
    }
  }

  @PostMapping("/{sucursalId}")
  public ResponseEntity<?> saveVenta(@RequestBody Venta venta, @PathVariable("sucursalId") Long id){
    try{

      SucursalDTO sucursalDTO = sucursalServicio.getSucursalById(id);

      if (sucursalDTO == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Sucursal no encontrada. ");
      }

      Set<Long> productosSucursalIds = sucursalDTO.getProductos()
          .stream()
          .map(ProductoDTO::getId)
          .collect(Collectors.toSet());

      boolean productosValidos = venta.getItems().stream()
          .allMatch(item -> productosSucursalIds.contains(item.getProducto().getId()));

      if (!productosValidos){
        return ResponseEntity
            .badRequest()
            .body("La sucursal no posee todos los productos de la venta");
      }
      ventaServicio.saveVenta(venta);

      return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada correctamente.");
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al crear la venta. ");
    }

  }

  @DeleteMapping("/{ventaID}")
  public ResponseEntity<?> deleteVenta(@PathVariable("ventaID")Long id){

    try{
      if(ventaServicio.getVentaById(id) != null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("La venta no Existe. ");
      }

      ventaServicio.deleteVentaById(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body("La venta fue eliminada. ");
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al eliminar la venta. ");
    }
  }

  @PutMapping
  public ResponseEntity<?> updateVenta(@RequestBody Venta venta){

    try {
      if (ventaServicio.getVentaById(venta.getId()) == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Venta no encontrada.");
      }
      this.ventaServicio.updateVenta(venta);
      return ResponseEntity.status(HttpStatus.OK)
          .body("Venta editada correctamente.");
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error al editar la venta. ");
    }
  }

}
