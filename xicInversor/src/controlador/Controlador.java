/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.arnau.persistencia.hibernate.HibernateUtil;
import java.util.List;
import modelo.Empresa;
import modelo.Pais;
import org.hibernate.HibernateException;
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
    
    public static Long ejecutarConsultaAgrupada(String consulta){
        
         HibernateUtil.openSessionAndBindToThread();
         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
         Query aux;
        try {
           
            return (long)session.createQuery(consulta).uniqueResult();
          
            
            
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
    public static Pais getPais(String nombre){
        HibernateUtil.openSessionAndBindToThread();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction beginTransaction = session.beginTransaction();
            Pais aux=(Pais)(Controlador.ejecutarConsulta("Select p FROM Pais p WHERE nombre='"+nombre+"'")).get(0);
                
            return aux;
         } 
        catch (IndexOutOfBoundsException iobe) {
            return null;
        }
        finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
         }
        
        
    }
    public static boolean borrarPais(Pais pais){
        
        
        System.out.println("oooooooooooooooo"+pais.getNombre());
        HibernateUtil.openSessionAndBindToThread();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction beginTransaction = session.beginTransaction();
            session.delete(pais);
            beginTransaction.commit();
        } catch (HibernateException he) {
            session.getTransaction().rollback();
            return false;
        } 
        catch (IllegalArgumentException iae) {
            return false;
        }
        finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        System.out.println("++++true");
        return true;
    }
    
    public static boolean modificarPais(Pais pais){
        HibernateUtil.openSessionAndBindToThread();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction beginTransaction = session.beginTransaction();
            session.update(pais);
            return true;
            
         } 
        catch (Exception e) {
            return false;
        }
        finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
         }
        
        
    }
    
    public static boolean insertarEmpresa(Empresa empr) {
        HibernateUtil.openSessionAndBindToThread();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction beginTransaction = session.beginTransaction();
            session.save(empr);
            beginTransaction.commit();
        } catch (ConstraintViolationException cve) {
            session.getTransaction().rollback();
            return false;
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        return true;
    }
    
    
}
