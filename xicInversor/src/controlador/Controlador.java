/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.arnau.persistencia.hibernate.HibernateUtil;
import java.util.List;
import modelo.Pais;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author hector
 */
public class Controlador {
    
    public static void iniciaHibernate(){        
        HibernateUtil.buildSessionFactory();
    }

    public static boolean insertarPais(Pais pais) {
        HibernateUtil.openSessionAndBindToThread();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction beginTransaction = session.beginTransaction();
            session.save(pais);
            beginTransaction.commit();
        } catch (ConstraintViolationException cve) {
            session.getTransaction().rollback();
            return false;
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        return true;
    }
    
    public static Object ejecutarConsultaAgrupada(String consulta){
        
         HibernateUtil.openSessionAndBindToThread();
         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
         Query aux;
        try {
           
            return session.createQuery(consulta).uniqueResult();
          
            
            
            } finally {
             HibernateUtil.closeSessionAndUnbindFromThread();
             
         }
    }
    
    public static List ejecutarConsulta(String consulta){
        
         HibernateUtil.openSessionAndBindToThread();
         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
         Query aux;
        try {
           
            return session.createQuery(consulta).list();
          
            
            
            } finally {
             HibernateUtil.closeSessionAndUnbindFromThread();
             
         }
    }
}
