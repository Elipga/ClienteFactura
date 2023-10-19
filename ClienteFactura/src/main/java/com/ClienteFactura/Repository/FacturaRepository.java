package com.ClienteFactura.Repository;

import com.ClienteFactura.Domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {
    List<Factura> findByDni(String dni);
    List<Factura> findByAnyoAndMes(int anyo, int mes);
}
