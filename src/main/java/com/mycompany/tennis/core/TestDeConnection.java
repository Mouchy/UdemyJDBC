package com.mycompany.tennis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4 
            //Class.forName(DRIVER_CLASS_NAME);
            
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","DBCOURS","DBCOURS");
            PreparedStatement preparedStatement = conn.prepareStatement("update JOUEUR SET NOM = ?, PRENOM = ? WHERE ID = ?");
            long identifiant  = 24L;
            String nom = "Errani";
            String prenom = "Sara";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3, identifiant);
           
            int nbEnregistrementsModifies = preparedStatement.executeUpdate();
            
            System.out.println("Nombre d'enregistrements modifiés="+nbEnregistrementsModifies);
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

