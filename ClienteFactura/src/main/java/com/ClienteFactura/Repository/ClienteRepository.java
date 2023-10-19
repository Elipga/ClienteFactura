package com.ClienteFactura.Repository;

import com.ClienteFactura.Domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByPremiumAndPaisOrderByNombre(Boolean premium, String pais);
}
