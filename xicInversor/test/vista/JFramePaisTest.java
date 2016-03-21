/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.arnau.persistencia.hibernate.HibernateUtil;
import modelo.Pais;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Héctor Arnau
 */
public class JFramePaisTest {
    
    Pais paisValido, paisInvalido;
    Session session;
    
    
    public JFramePaisTest() {
    }
    
    @BeforeClass
    protected void setUpClass() {
        paisValido=new Pais("Nombreinventado");
        
        HibernateUtil.openSessionAndBindToThread();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
    }
    
    @AfterClass
    public void tearDownClass() {
        
        Transaction beginTransaction = session.beginTransaction();
        session.delete(paisValido);                
        beginTransaction.commit();
       
        
    }

    void testInsertaPaisValido(){
        
        Transaction beginTransaction = session.beginTransaction();
        session.save(paisValido);                
        beginTransaction.commit();
      
        
    }
    
}
