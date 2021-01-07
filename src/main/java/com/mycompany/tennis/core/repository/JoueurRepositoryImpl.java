package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;

public class JoueurRepositoryImpl {

	public void create(Joueur joueur) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			session.persist(joueur);
			tx.commit();
			System.out.println("joueur créé");
		} catch (Exception e) {
			if (tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if (session!=null) {
				session.close();
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
		Joueur joueur = new Joueur();
		joueur.setId(id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(joueur);
		System.out.println("Tournoi effacer");
	}
	
	public Joueur getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Joueur joueur = session.get(Joueur.class, id);
		System.out.println("Joueur lu");
		
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
