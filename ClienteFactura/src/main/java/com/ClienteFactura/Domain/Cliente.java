package com.ClienteFactura.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor //constructor sin argumentos
public class Cliente {
    @Id
    @NotNull(message = "Dni no puede ser null")
    @NotEmpty(message = "Dni no puede ser null")
    private String dni;

    private String nombre;

    @Past(message = "No puede ser una fecha futura") //(yyyy-mm-dd)
    //@Temporal(TemporalType.DATE)
    private Date fechaNac;

    private String pais;
    private boolean premium; //valor por defecto: false

    public Cliente(String dni) {//parametros obligatorios

        this.dni = dni;
    }

    public Cliente(String dni, String nombre, Date fechaNac, String pais, boolean premium) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.pais = pais;
        this.premium = premium;
    }

}
