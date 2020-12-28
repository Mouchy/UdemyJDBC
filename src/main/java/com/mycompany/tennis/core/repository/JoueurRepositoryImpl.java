package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;

public class JoueurRepositoryImpl {

	public void create(Joueur joueur) {
		Connection conn = null;
        try {
        	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	
        	conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
           
            preparedStatement.executeUpdate();
            
            System.out.println("Joueur créé");
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
	
	public void update(Joueur joueur) {
		Connection conn = null;
	    try {
	    	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
	    	
	    	conn = dataSource.getConnection();
	
	        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR set NOM=?, PRENOM=?, SEXE=? where ID = ?");
	
	        preparedStatement.setString(1, joueur.getNom());
	        preparedStatement.setString(2, joueur.getPrenom());
	        preparedStatement.setString(3, joueur.getSexe().toString());
	        preparedStatement.setLong(4, joueur.getId());
	       
	        preparedStatement.executeUpdate();
	        
	        System.out.println("Joueur modifié");
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
	
	public void delete(Long id) {
		Connection conn = null;
	    try {
	    	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
	    	
	    	conn = dataSource.getConnection();
	
	        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID = ?");
	
	        preparedStatement.setLong(1, id);
	       
	        preparedStatement.executeUpdate();
	        
	        System.out.println("Joueur suprimé");
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
	
	public Joueur getById(Long id) {
		Connection conn = null;
		Joueur joueur = null;
	    try {
	    	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
	    	
	    	conn = dataSource.getConnection();
	
	        PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID = ?");
	
	        preparedStatement.setLong(1, id);
	       
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        if (rs.next()) {
	        	joueur = new Joueur();
	        	joueur.setId(id);
	        	joueur.setNom(rs.getString("NOM"));
	        	joueur.setPrenom(rs.getString("PRENOM"));
	        	joueur.setSexe(rs.getString("SEXE").charAt(0));
	        }
	        
	        System.out.println("Joueur lu");
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
	    return joueur;
	}
	
	public List<Joueur> list() {
		Connection conn = null;
		List<Joueur> joueurs = new ArrayList<>();
	    try {
	    	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
	    	
	    	conn = dataSource.getConnection();
	
	        PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR");
	       
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        while (rs.next()) {
	        	Joueur joueur = new Joueur();
	        	joueur.setId(rs.getLong("ID"));
	        	joueur.setNom(rs.getString("NOM"));
	        	joueur.setPrenom(rs.getString("PRENOM"));
	        	joueur.setSexe(rs.getString("SEXE").charAt(0));
	        	joueurs.add(joueur);
	        }
	        
	        System.out.println("Joueurs lus");
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
	    return joueurs;
	}
}
