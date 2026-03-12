package org.Mercap.servicios;

import org.Mercap.DTO.ProductoDTO;
import org.Mercap.dominio.Producto;
import org.Mercap.repositorios.ProductoRepository;
import org.Mercap.repositorios.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServicio {

  @Autowired
  private ProductoRepository productoRepository;

  public List<ProductoDTO> getProductoList(){
    return this.productoRepository.findAll().stream().map(ProductoDTO::new).collect(Collectors.toList());
  }

  public ProductoDTO getProductoById(Long id){
    return this.productoRepository.findById(id).map(ProductoDTO::new).orElse(null);
  }

  public void saveProducto(Producto producto){
    this.productoRepository.save(producto);
  }

  public void deleteProductoById(Long id){
    this.productoRepository.deleteById(id);
  }

  public ProductoDTO updateProducto(Producto producto){

    return this.productoRepository.findById(producto.getId()).map(producto1 -> {
      producto1.setDescripcion(producto.getDescripcion());
      producto1.setNombre(producto.getNombre());
      producto1.setPrecioActual(producto.getPrecioActual());
      productoRepository.save(producto1);
      return new ProductoDTO(producto1);
    }).orElse(null);
  }

  public List<ProductoDTO> getProductoBySucursal(Long id){
    List<Producto> productos = this.productoRepository.findBySucursal(id);
    return productos.stream().map(ProductoDTO::new).collect(Collectors.toList());
  }
}
