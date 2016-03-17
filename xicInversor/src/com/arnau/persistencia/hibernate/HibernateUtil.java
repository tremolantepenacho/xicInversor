/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arnau.persistencia.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
 
      public static synchronized void buildSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
             configuration.configure();
             configuration.setProperty("hibernate.current_session_context_class", "thread");
             configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
             ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
             sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         }
     }

     public static void openSessionAndBindToThread() {
         Session session = sessionFactory.openSession();
         ThreadLocalSessionContext.bind(session);
     }


     public static SessionFactory getSessionFactory() {
         if (sessionFactory==null)  {
             buildSessionFactory();
         }
         return sessionFactory;
     }

     public static void closeSessionAndUnbindFromThread() {
         Session session = ThreadLocalSessionContext.unbind(sessionFactory);
         if (session!=null) {
             session.close();
         }
     }

     public static void closeSessionFactory() {
         if ((sessionFactory!=null) && (sessionFactory.isClosed()==false)) {
             sessionFactory.close();
         }
     }
     
     public static void crearBD(){
          Configuration configuration = new Configuration();
             configuration.configure();
             configuration.setProperty("hibernate.current_session_context_class", "thread");
             configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
         //Si segundo parámetro del método create está a true, Hibernate crea la BD pero borra los datos
             new org.hibernate.tool.hbm2ddl.SchemaExport(configuration).setOutputFile("script.sql").setDelimiter(";").create(true, true);
     }
 }
