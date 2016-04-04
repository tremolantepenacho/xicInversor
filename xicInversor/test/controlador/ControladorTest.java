/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.arnau.persistencia.hibernate.HibernateUtil;
import java.util.Date;
import java.util.List;
import modelo.Pais;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    
    static Pais paisRepetido, paisValido;
    static Session session;
    
    public ControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        paisRepetido=new Pais(new Date().toString());
        paisValido=new Pais(new Date().toString()+"a");
        
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
        List resultado=Controlador.ejecutarConsulta("SELECT pa FROM Pais WHERE nombre='Torrent'");
        assertEquals(resultado.get(0).toString(),"Torrent");
      
    }
    
    /**
     * Test of insertarPais method, of class Controlador.
     */
   /* @Test
    public void testInsertarPais() {
        Query query = session.createQuery("SELECT COUNT(p) FROM Pais p");
        long antes=(long)query.uniqueResult();
        Controlador.insertarPais(paisValido);
       // Transaction beginTransaction = session.beginTransaction();
        //session.save(paisValido);                
        //beginTransaction.commit();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        query = session.createQuery("SELECT COUNT(p) FROM Pais p");
        long despues=(long)query.uniqueResult();
        Assert.assertEquals(antes+1, despues);
        session.delete(paisValido);
    }
    */
}



    
    
    
    
    
       
        
   

    
    /*
     * Inserto un pais, cuento cuantos hay, lo vuelvo a insertar, compruebo que no se inserte
     * y lo borro para dejar la BD igual que antes
    */
   /* @Test
    public void testInsertaPaisRepetido(){
                
        Transaction beginTransaction = session.beginTransaction();
        session.save(paisRepetido); 
        Query query = session.createQuery("SELECT COUNT(p) FROM Pais p");
        long antes=(long)query.uniqueResult();
        session.save(paisRepetido);                
        beginTransaction.commit();
        long despues=(long)query.uniqueResult();
        Assert.assertEquals(antes, despues);
        session.delete(paisRepetido);
     
        
    }
    
    @Test
    public void testInsertaPais(){
        
        Query query = session.createQuery("SELECT COUNT(p) FROM Pais p");
        long antes=(long)query.uniqueResult();
        Transaction beginTransaction = session.beginTransaction();
        session.save(paisValido);                
        beginTransaction.commit();
        long despues=(long)query.uniqueResult();
        Assert.assertEquals(antes+1, despues);
        session.delete(paisValido);
     
        
    }
    
}
*/