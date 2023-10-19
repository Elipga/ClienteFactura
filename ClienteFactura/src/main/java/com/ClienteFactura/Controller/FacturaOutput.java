package com.ClienteFactura.Controller;

import com.ClienteFactura.Domain.Factura;
import lombok.Getter;

import javax.validation.constraints.*;


@Getter
public class FacturaOutput {
    @NotNull(message = "Código de factura no puede ser null")
    @NotEmpty(message = "Código de factura no puede ser null")
    private String codFactura;

    @Min(value = 0, message = "El total no puede ser negativo")
    private float total;

    @Min(value = 1, message = "El mes debe ser igual o mayor que 1")
    @Max(value = 12, message = "El mes debe ser igual o menor que 12")
    private int mes;

    @Min(value = 1900, message = "El año debe ser igual o mayor que 1900")
    @Max(value = 2023, message = "El año debe ser igual o menor que 2023")
    private int anyo;

    @NotNull(message = "Dni no puede ser null")
    @NotEmpty(message = "Dni no puede ser null")
    private String dni;

    public FacturaOutput(String codFactura, float total, int mes, int anyo, String dni) {
        this.codFactura = codFactura;
        this.total = total;
        this.mes = mes;
        this.anyo = anyo;
        this.dni = dni;
    }

    public static FacturaOutput getFactura(Factura factura) {
        return new FacturaOutput(factura.getCodFactura(), factura.getTotal(), factura.getMes(),
                factura.getAnyo(),factura.getDni());
    }
}
