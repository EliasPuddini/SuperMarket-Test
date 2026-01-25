package org.Mercap.controlador;

import org.Mercap.DTO.ProductoDTO;
import org.Mercap.dominio.Producto;
import org.Mercap.servicios.ProductoServicio;
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
@RequestMapping(path = "api/productos")
public class ProductoControlador {

  @Autowired
  private ProductoServicio productoServicio;

  @GetMapping
  public List<ProductoDTO> getList(){
    return this.productoServicio.getProductoList();
  }

  @GetMapping("/{productoID}")
  public ProductoDTO getById(@PathVariable("productoID")Long id){
    return this.productoServicio.getProductoById(id);
  }

  @PostMapping
  public void SaveProducto(@RequestBody Producto producto){
    this.productoServicio.saveProducto(producto);
  }

  @DeleteMapping("/productoID")
  public void deleteProducto(@PathVariable("productoID") Long id){
    this.productoServicio.deleteProductoById(id);
  }


  @PutMapping
  public void updateProducto(@RequestBody Producto producto){
    this.productoServicio.updateProducto(producto);
  }

}
