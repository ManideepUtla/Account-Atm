package com.example.fullstackdemo310824.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connection; //instance varaible and instance varaible having null

    public static Connection getConnection(){
        try{
            if(connection==null) {
                System.out.println("Getting connection");

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://@localhost:3306/bank", "root", "Manideep2##2");
            }else{
                System.out.println("Returing existing connection");
            }

        }catch (Exception ex){
            //swallowing the exception
            System.out.println("Exception Occured in getconnection "+ex);

        }
        return connection;


    }

}
