package org.Mercap.controlador;

import org.Mercap.DTO.VentaDTO;
import org.Mercap.dominio.Venta;
import org.Mercap.servicios.VentaServicio;
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
@RequestMapping("api/ventas")
public class VentaControlador {

  @Autowired
  private VentaServicio ventaServicio;

  @GetMapping
  public List<VentaDTO> getList(){
    return this.ventaServicio.getVentaList();
  }

  @GetMapping("/{ventaID}")
  public VentaDTO getById(@PathVariable("ventaID")Long id){
    return this.ventaServicio.getVentaById(id);
  }

  @PostMapping()
  public void saveVenta(@RequestBody Venta venta){
    this.ventaServicio.saveVenta(venta);
  }

  @DeleteMapping("/{ventaID}")
  public void deleteVenta(@PathVariable("ventaID")Long id){
    this.ventaServicio.deleteVentaById(id);
  }

  @PatchMapping
  @PutMapping
  public void updateVenta(@RequestBody Venta venta){
    this.ventaServicio.updateVenta(venta);
  }

}
