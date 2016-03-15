/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotEmpty;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;    

    @Column(name="Nombre",unique=true)
    @NotEmpty(message="El nombre es obligatorio")
    private String nombre;
    
    @Column(name="Cantidad")
    private int cantidad;
     
    @Column(name="Pais")
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Pais pais;
    
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="Empresa")
    @IndexColumn(name="Fecha")
    private List<Compra> compras;
    
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="Empresa")
    @IndexColumn(name="Fecha")
    private List<Venta> ventas;
    
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="Dividendo")
    @IndexColumn(name="Fecha")
    private List<Dividendo> dividendos;
     
    public Empresa(){
        
    }

   
    public Empresa(String nombre, int cantidad, Pais pais) {
        this.nombre = nombre;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
