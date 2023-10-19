package com.ClienteFactura.Controller;

import com.ClienteFactura.Domain.Factura;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class FacturaOutputCodYTotal {
    @NotNull(message = "Código de factura no puede ser null")
    @NotEmpty(message = "Código de factura no puede ser null")
    private String codFactura;

    @Min(value = 0, message = "El total no puede ser negativo")
    private float total;

    public FacturaOutputCodYTotal(String codFactura, float total) {
        this.codFactura = codFactura;
        this.total = total;
    }

    public static FacturaOutputCodYTotal getFactura(Factura factura) {
        return new FacturaOutputCodYTotal(factura.getCodFactura(), factura.getTotal());
    }
}