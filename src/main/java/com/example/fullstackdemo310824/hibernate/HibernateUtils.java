package com.example.fullstackdemo310824.hibernate;


import com.example.fullstackdemo310824.model.AadharEntity;
import com.example.fullstackdemo310824.model.AccountAddressEntity;
import com.example.fullstackdemo310824.model.AccountEntity;
import com.example.fullstackdemo310824.model.AddressEntity;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtils {
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory==null){

            Configuration configuration=new Configuration(); //object creation
            Properties properties=new Properties();
            properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");//Driver is static and default interface is static
            properties.put(Environment.URL,"jdbc:mysql://@localhost:3306/bank");
            properties.put(Environment.USER,"root");
            properties.put(Environment.PASS,"Manideep2##2");
            properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
            properties.put(Environment.SHOW_SQL,true);

            //setting the data using setproperties or setProperty in this we are using setProperties
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(AccountEntity.class)
                    .addAnnotatedClass(AadharEntity.class)
                    .addAnnotatedClass(AddressEntity.class)
                    .addAnnotatedClass(AccountAddressEntity.class);
            ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory=configuration.buildSessionFactory(serviceRegistry);
            System.out.println("session  created");

        }else {
            System.out.println("session not created");
        }
        return sessionFactory;

    }


}
