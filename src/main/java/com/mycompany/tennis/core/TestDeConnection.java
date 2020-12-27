package com.mycompany.tennis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
        	MysqlDataSource dataSource = new MysqlDataSource();
        	dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
        	dataSource.setUser("DBCOURS");
        	dataSource.setPassword("DBCOURS");
        	
        	conn = dataSource.getConnection();
            //Seulement avant Java 7/JDBC 4 
            //Class.forName(DRIVER_CLASS_NAME);
            
            //MySQL driver MySQL Connector
           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","manu","xevrod2x");
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");
           
            String nom    = "Capriati";
            String prenom = "Jennifer";
            String sexe   = "F";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);
           
            preparedStatement.executeUpdate();
            
            nom    = "Johannson";
            prenom = "Thomas";
            sexe   = "H";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);
           
            preparedStatement.executeUpdate();
            
            conn.commit();
            

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
            	if (conn!=null) conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
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

