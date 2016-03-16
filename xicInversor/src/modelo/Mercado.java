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
 * @fecha 14/03/2016 12:24:09
 * @company DAM
 */

@Entity
@Table(name="Mercado")
public class Mercado implements Serializable {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;    

    @Column(name="Nombre",unique=true)
    @NotEmpty(message="El nombre es obligatorio")
    private String nombre;
    
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Pais pais;

    public Mercado() {
    }

    public Mercado(String nombre, Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    
}
