/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Héctor Arnau
 * @fecha 14/03/2016 12:39:19
 * @company DAM
 */
public class Dividendo implements Serializable{
    
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;    

    
    @Column(name="Acciones")
    private int acciones;
    
    @Column(name="Dinero")
    private float dinero;
    
    
    @Column(name="Fecha")
    @Temporal(TemporalType.DATE)
    @NotEmpty(message="La fecha es obligatoria")
    private Date fecha;
     
  //  @Column(name="Empresa")
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Empresa empresa;
    
    public Dividendo() {
    }

    public Dividendo(int acciones, float dinero, Date fecha, Empresa empresa) {
        this.acciones = acciones;
        this.dinero = dinero;
        this.fecha = fecha;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcciones() {
        return acciones;
    }

    public void setAcciones(int acciones) {
        this.acciones = acciones;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    
}
