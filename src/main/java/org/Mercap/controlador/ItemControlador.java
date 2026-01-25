package org.Mercap.controlador;

import org.Mercap.servicios.ItemServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/Item")
public class ItemControlador {

  @Autowired
  private ItemServicio itemServicio;

}
