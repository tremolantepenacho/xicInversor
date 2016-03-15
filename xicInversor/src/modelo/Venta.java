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
 * @fecha 14/03/2016 12:36:59
 * @company DAM
 */
@Entity
@Table(name="Venta")
public class Venta implements Serializable{

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;    
    
    @Column(name="Cantidad")
    private int cantidad;
    
    @Column(name="Precio")
    private float precio;
    
    @Column(name="Fecha")
    @Temporal(TemporalType.DATE)
    @NotEmpty(message="La fecha es obligatoria")
    private Date fecha;
     
    @Column(name="Empresa")
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Empresa empresa;

    public Venta() {
    }

    /**
     *
     * @param cantidad
     * @param precio
     * @param fecha
     * @param empresa
     */
    public Venta(int cantidad, float precio, Date fecha, Empresa empresa) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
