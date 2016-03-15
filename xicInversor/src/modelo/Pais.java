/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Héctor Arnau
 * @fecha 14/03/2016 12:17:43
 * @company DAM
 */

@Entity
@Table(name="Pais")
public class Pais implements Serializable{

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    

    @Column(name="nombre",unique=true)
    @NotEmpty(message="El nombre es obligatorio")
    private String nombre;

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public Pais() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
