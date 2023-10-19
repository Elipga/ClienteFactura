package com.ClienteFactura.Controller;

import com.ClienteFactura.Domain.Cliente;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
public class ClienteInput {
    @NotNull(message = "Dni no puede ser null")
    @NotEmpty(message = "Dni no puede ser null")
    private String dni;

    private String nombre;

    @Past(message = "No puede ser una fecha futura")
    private Date fechaNac;
    private String pais;
    private boolean premium;

    public ClienteInput(String dni, String nombre, Date fechaNac, String pais, boolean premium) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.pais = pais;
        this.premium = premium;
    }

    public static Cliente getCliente(ClienteInput clienteInput){
        return new Cliente(clienteInput.getDni(), clienteInput.getNombre(), clienteInput.getFechaNac(), clienteInput.pais,
                clienteInput.isPremium());
    }
}
