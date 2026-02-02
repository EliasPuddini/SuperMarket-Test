package org.Mercap.servicios;

import org.Mercap.DTO.SucursalDTO;
import org.Mercap.dominio.Sucursal;
import org.Mercap.repositorios.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalServicio {
  @Autowired
  private SucursalRepository sucursalRepository;


  @Transactional(readOnly = true)
  public List<SucursalDTO> getSucursalList(){
    return this.sucursalRepository.findAll().stream().map(SucursalDTO::new).collect(Collectors.toList());
  }

  public SucursalDTO getSucursalById(Long id){
    return this.sucursalRepository.findById(id).map(SucursalDTO::new).orElse(null);
  }

  public void saveSucursal(Sucursal sucursal){
    this.sucursalRepository.save(sucursal);
  }

  public void deleteSucursalById(Long id){
    this.sucursalRepository.deleteById(id);
  }

  public SucursalDTO updateSucursal(Sucursal sucursal){
    return this.sucursalRepository.findById(sucursal.getId()).map(sucursal1 -> {
      sucursal1.setNombre(sucursal.getNombre());
      sucursal1.setProductos(sucursal.getProductos());
      sucursal1.setVentas(sucursal.getVentas());

      sucursalRepository.save(sucursal1);
      return new SucursalDTO(sucursal1);
    }).orElse(null);
  }

  public boolean existSucursalByID(Long id){
    return this.sucursalRepository.existsById(id);
  }
}
