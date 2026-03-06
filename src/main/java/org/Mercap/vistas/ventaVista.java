package org.Mercap.vistas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventas")
public class ventaVista {

  @GetMapping
  public String getAll(){
    return "ventas";
  }

  @GetMapping("/{ventaId}")
  public String get(@PathVariable("ventaId") Long id){
    return "ventas";
  }

  @GetMapping("/nuevaVenta")
  public String nuevaVenta(){
    return "nuevaVenta";
  }
}
