/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.arnau.persistencia.hibernate.HibernateUtil;
import java.util.Date;
import java.util.List;
import modelo.Empresa;
import modelo.Pais;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hector
 */
public class ControladorTest {
    
    static Pais paisRepetido, paisValido, paisNoInsertado;
    static Session session;
    
    public ControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        paisRepetido=new Pais(new Date().toString());
        paisValido=new Pais(new Date().toString()+"a");
        paisNoInsertado=new Pais(new Date().toString()+"aaa");
        
       Controlador.iniciaHibernate();
      
    }
    
    @AfterClass
    public static void tearDownClass() {
    
        HibernateUtil.closeSessionAndUnbindFromThread();
        HibernateUtil.closeSessionFactory();        
        
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of ejecutarConsulta method, of class Controlador.
     */
    @Test
    public void testEjecutarConsultaAgrupadaCorrecta(){
        long resultado=Controlador.ejecutarConsultaAgrupada("SELECT COUNT(p) FROM Pais p WHERE nombre='Torrent'");
        assertEquals(1, resultado);         
   
        
    }
    @Test
    public void testEjecutarConsultaAgrupadaVacia(){
        long resultado=Controlador.ejecutarConsultaAgrupada("SELECT COUNT(p) FROM Pais p WHERE nombre='paisinexistente"+new Date().toString()+"'");
        assertEquals(0, resultado);   
          
   
        
    }

    /**
     * Test of iniciaHibernate method, of class Controlador.
     */
    @Test
    public void testIniciaHibernate() {
       Assert.assertNotNull(HibernateUtil.getSessionFactory());
    }

   
    /**
     * Test of ejecutarConsulta method, of class Controlador.
     */
    @Test
    public void testEjecutarConsultaVacia() {
        List resultado=Controlador.ejecutarConsulta("SELECT p FROM Pais p WHERE nombre='paiscacacaca'");
        assertTrue(resultado.isEmpty()==true);
      
    }
    
    /**
     * Test of ejecutarConsulta method, of class Controlador.
     */
    @Test
    public void testEjecutarConsultaCorrecta() {
        List resultado=Controlador.ejecutarConsulta("SELECT pa FROM Pais pa WHERE nombre='Torrent'");
        assertEquals(((Pais)resultado.get(0)).getNombre(),"Torrent");
      
    }
    
    /**
     * Test of insertarPais method, of class Controlador.
     */
    @Test
    public void testInsertarPaisValido() {
        
        long antes=Controlador.ejecutarConsultaAgrupada("SELECT COUNT(p) FROM Pais p");
        Controlador.insertarPais(paisValido);
        long despues=Controlador.ejecutarConsultaAgrupada("SELECT COUNT(p) FROM Pais p");
        Assert.assertEquals(antes+1, despues);
        Controlador.borrarPais(paisValido);
    }
    
    /**
     * Test of insertarPais method, of class Controlador.
     */
    @Test
    public void testInsertarPaisRepetido() {
        
        Controlador.insertarPais(paisValido);
        assertFalse(Controlador.insertarPais(paisValido));
        Controlador.borrarPais(paisValido);
    }

   
    /**
     * Test of borrarPais method, of class Controlador.
     */
    @Test
    public void testBorrarPaisExistente() {
        Controlador.insertarPais(paisValido);
        long result=Controlador.ejecutarConsultaAgrupada("SELECT COUNT(p) FROM Pais p WHERE nombre='"+paisValido.getNombre()+"'");
        assertEquals(1,result);
        assertTrue(Controlador.borrarPais(paisValido));
        result=Controlador.ejecutarConsultaAgrupada("SELECT COUNT(p) FROM Pais p WHERE nombre='"+paisValido.getNombre()+"'");
        assertEquals(0,result);
        
    }
    
     /**
     * Test of borrarPais method, of class Controlador.
     */
    @Test
    public void testBorrarPaisInexistente() {
        System.out.println("pepe"+Controlador.borrarPais(paisNoInsertado));
        assertFalse(Controlador.borrarPais(paisNoInsertado));
             
    }

    
}