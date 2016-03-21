/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.arnau.persistencia.hibernate.HibernateUtil;
import java.util.Date;
import modelo.Pais;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Héctor Arnau
 */
public class JFramePaisTest {
    
    static Pais paisRepetido, paisValido;
    static Session session;
    
    
    public JFramePaisTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        paisRepetido=new Pais(new Date().toString());
        paisValido=new Pais(new Date().toString()+"a");
        
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSessionAndBindToThread();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        Transaction beginTransaction = session.beginTransaction();
        session.delete(paisRepetido);                
        beginTransaction.commit();
        
        HibernateUtil.closeSessionAndUnbindFromThread();
        HibernateUtil.closeSessionFactory();
       
        
    }

    
    /*
     * Inserto un pais, cuento cuantos hay, lo vuelvo a insertar, compruebo que no se inserte
     * y lo borro para dejar la BD igual que antes
    */
    @Test
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
