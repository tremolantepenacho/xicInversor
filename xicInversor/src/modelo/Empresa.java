/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

/**
 *
 * @author Hector Arnau Aparicio
 * @fecha 02/03/2016 14:05:53
 */

 @Entity
 @Table(name="Empresa")
public class Empresa implements Serializable{

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = AUTO)
    private int id;

    @Column(name="nombre")
    private String nombre;
    
    public Empresa(){
        
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
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
