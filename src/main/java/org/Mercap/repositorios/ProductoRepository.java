package org.Mercap.repositorios;

import org.Mercap.dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

  @Query(value = """
    SELECT p.*
    FROM producto p
    JOIN sucursalxproducto sp ON p.id = sp.producto_id
    WHERE sp.sucursal_id = :id
""", nativeQuery = true)
  List<Producto> findBySucursal(@Param("id") Long id);
}
