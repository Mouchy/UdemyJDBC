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

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;

public class TournoiRepositoryImpl {

	public void create(Tournoi tournoi) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.persist(tournoi);
		System.out.println("Tournoi créé");
    }
	
	public void update(Tournoi tournoi) {
		Connection conn = null;
	    try {
	    	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
	    	
	    	conn = dataSource.getConnection();
	
	        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI set NOM=?, CODE=? where ID = ?");
	
	        preparedStatement.setString(1, tournoi.getNom());
	        preparedStatement.setString(2, tournoi.getCode());
	        preparedStatement.setLong(3, tournoi.getId());

	       
	        preparedStatement.executeUpdate();
	        
	        System.out.println("Tournoi modifié");
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
		Tournoi tournoi = new Tournoi();
		tournoi.setId(id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(tournoi);
		System.out.println("Tournoi effacer");
	}
	
	public Tournoi getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Tournoi tournoi = session.get(Tournoi.class, id);
		System.out.println("Tournoi lu");
		
		return tournoi;
	}
	
	public List<Tournoi> list() {
		Connection conn = null;
		List<Tournoi> tournois = new ArrayList<>();
	    try {
	    	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
	    	
	    	conn = dataSource.getConnection();
	
	        PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM TOURNOI");
	       
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        while (rs.next()) {
	        	Tournoi tournoi = new Tournoi();
	        	tournoi.setId(rs.getLong("ID"));
	        	tournoi.setNom(rs.getString("NOM"));
	        	tournoi.setCode(rs.getString("CODE"));

	        	tournois.add(tournoi);
	        }
	        
	        System.out.println("Tournoi lus");
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
	    return tournois;
	}
}
