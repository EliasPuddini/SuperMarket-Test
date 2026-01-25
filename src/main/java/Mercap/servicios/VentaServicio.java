package org.Mercap.servicios;

import org.Mercap.DTO.VentaDTO;
import org.Mercap.dominio.Venta;
import org.Mercap.repositorios.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServicio {

  @Autowired
  private VentaRepository ventaRepository;

  public List<VentaDTO> getVentaList(){
    return this.ventaRepository.findAll().stream().map(VentaDTO::new).collect(Collectors.toList());
  }

  public VentaDTO getVentaById(Long id){
    return this.ventaRepository.findById(id).map(VentaDTO::new).orElse(null);
  }

  public void saveVenta(Venta venta){
    this.ventaRepository.save(venta);
  }

  public void deleteVentaById(Long id){
    this.ventaRepository.deleteById(id);
  }

  public VentaDTO updateVenta(Venta venta){
    return this.ventaRepository.findById(venta.getId()).map(venta1 -> {
      venta1.setFecha(venta.getFecha());
      venta1.setItems(venta.getItems());

      ventaRepository.save(venta1);
      return new VentaDTO(venta1);
    }).orElse(null);
  }

}
