package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.hibernate.Session;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;

public class MatchRepositoryImpl {

	public void create(Match match) {
		Connection conn = null;
        try {
        	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	
        	conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());
           
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            
            if (rs.next()) {
            	System.out.println("Match créé");
            	match.setId(rs.getLong(1));
            	System.out.println("Match créé "+match.getId());
            }
            
            System.out.println("Match créé");
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
	
	public Match getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Match match = session.get(Match.class, id);
		System.out.println("Match lu");
		
		return match;
	}
}
