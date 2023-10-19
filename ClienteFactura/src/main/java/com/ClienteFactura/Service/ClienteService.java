package com.ClienteFactura.Service;

import com.ClienteFactura.Controller.*;
import com.ClienteFactura.Domain.Cliente;
import com.ClienteFactura.Domain.Factura;
import com.ClienteFactura.Exception.AlreadyExistsException;
import com.ClienteFactura.Exception.IsEmptyException;
import com.ClienteFactura.Exception.NotExistsException;
import com.ClienteFactura.Repository.ClienteRepository;
import com.ClienteFactura.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    public ClienteService() {
        this.clienteRepository = clienteRepository;
    }

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void anyadirCliente(ClienteInput clienteInput) throws AlreadyExistsException {
        Cliente nuevocliente = ClienteInput.getCliente(clienteInput); //convierte clienteInput en cliente
        if(clienteRepository.existsById(clienteInput.getDni()))
            throw new AlreadyExistsException("El cliente ya existe");
        else clienteRepository.save(nuevocliente); //añade el nuevo cliente
    }

    public List<ClienteOutput> getClientesByPremiumYPais(boolean premium, String pais) throws IsEmptyException {
        List<Cliente> clientes = clienteRepository.findByPremiumAndPaisOrderByNombre(premium, pais);
        List<ClienteOutput> clientesOutput = new ArrayList<>();
        if(clientes.isEmpty()) throw new IsEmptyException("No hay clientes de ese tipo y país");

        for(Cliente cliente: clientes){
            clientesOutput.add(ClienteOutput.getCliente(cliente));
        }
        return clientesOutput;
    }

    public List<ClienteOutput> getClientes() throws IsEmptyException {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteOutput> clientesOutput = new ArrayList<>();
        if(clientes.isEmpty()) throw new IsEmptyException("No hay clientes");

        for(Cliente cliente: clientes){
            clientesOutput.add(ClienteOutput.getCliente(cliente));
        }
        return clientesOutput;
    }

    public void anyadirFacturaACliente(String dni, FacturaInput facturaInput) throws NotExistsException, AlreadyExistsException {
        //No se puede crear una factura sin cliente. La factura SIEMPRE va asociada a un cliente
        if(!clienteRepository.existsById(dni)) throw new NotExistsException("El cliente no existe");
        if(facturaRepository.existsById(facturaInput.getCodFactura())) throw new AlreadyExistsException("La factura ya existe");
        facturaRepository.save(FacturaInput.getFactura(facturaInput)); //añade la nueva factura
    }

    public List<FacturaOutputCodYTotal> getFacturasCliente(String dni) throws NotExistsException, IsEmptyException {
        if(!clienteRepository.existsById(dni)) throw new NotExistsException("El cliente no existe");

        List<Factura> facturasByDni = facturaRepository.findByDni(dni); //todas las facturas filtradas por dni
        List<FacturaOutputCodYTotal> facturasOutputCodYTotals = new ArrayList<>();
        if(facturasByDni.isEmpty()) throw new IsEmptyException("El cliente no tiene facturas");

        for(Factura factura: facturasByDni){
            facturasOutputCodYTotals.add(FacturaOutputCodYTotal.getFactura(factura));
        }
        return facturasOutputCodYTotals;
    }
}
