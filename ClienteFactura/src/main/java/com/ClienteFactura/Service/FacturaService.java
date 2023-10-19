package com.ClienteFactura.Service;

import com.ClienteFactura.Controller.FacturaOutput;
import com.ClienteFactura.Domain.Factura;
import com.ClienteFactura.Exception.IsEmptyException;
import com.ClienteFactura.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public FacturaService() {
        this.facturaRepository = facturaRepository;
    }

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaOutput> getFacturas() throws IsEmptyException {
        List<Factura> facturas = facturaRepository.findAll(); //lista de todas las facturas
        List<FacturaOutput> facturaOutputs = new ArrayList<>(); // crea lista de FacturasOutput
        if(facturas.isEmpty()) throw new IsEmptyException("Todavía no hay facturas");

        for(Factura factura: facturas){ //recorre facturas
            facturaOutputs.add(FacturaOutput.getFactura(factura)); //añade a facturasOutput
        }
        return facturaOutputs;
    }

    public List<FacturaOutput> getFacturasByAnyoYMes(int anyo, int mes) throws IsEmptyException {
        List<Factura> facturas = facturaRepository.findByAnyoAndMes(anyo, mes); //lista de todas las facturas por año y mes
        List<FacturaOutput> facturasOutput = new ArrayList<>(); //crea lista facturasOutput
        if(facturas.isEmpty()) throw new IsEmptyException("No hay facturas de ese año y mes");


        for(Factura factura: facturas){ //recorre las facturas por año y mes
            facturasOutput.add(FacturaOutput.getFactura(factura)); //añade a facturasOutput
        }
        return facturasOutput;
    }
}
