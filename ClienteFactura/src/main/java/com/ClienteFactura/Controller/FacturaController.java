package com.ClienteFactura.Controller;

import com.ClienteFactura.Exception.IsEmptyException;
import com.ClienteFactura.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FacturaController {

    @Autowired
    FacturaService facturaService;

    @GetMapping("/facturas")
    public ResponseEntity<List<FacturaOutput>> getFacturas(){
        try {
            List<FacturaOutput> facturas = facturaService.getFacturas();
            return ResponseEntity.ok(facturas);
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @RequestMapping(value = "/facturas", params = {"anyo", "mes"})
    public ResponseEntity<List<FacturaOutput>> getFacturasByAnyoYMes(@RequestParam int anyo, @RequestParam int mes){
        List<FacturaOutput> facturasByAnyoYMes = null;
        try {
            facturasByAnyoYMes = facturaService.getFacturasByAnyoYMes(anyo, mes);
            return ResponseEntity.ok(facturasByAnyoYMes);
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }


}
