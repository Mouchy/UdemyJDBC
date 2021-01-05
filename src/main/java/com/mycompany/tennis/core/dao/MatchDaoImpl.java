package com.mycompany.tennis.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;

public class MatchDaoImpl {
	public void createMatchwithScore(Match match) {
		Connection conn = null;
        try {
        	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	
        	conn = dataSource.getConnection();
        	/********************************/
        	conn.setAutoCommit(false);
        	/********************************/
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
            
            
            PreparedStatement preparedStatement2 = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement2.setLong(1, match.getScore().getMatch().getId());
            preparedStatement2.setByte(2, match.getScore().getSet1());
            preparedStatement2.setByte(3, match.getScore().getSet2());
            preparedStatement2.setByte(4, match.getScore().getSet3());
            if (match.getScore().getSet4() == null) {
            	preparedStatement2.setNull(5, Types.TINYINT);
            }
            else {
            	preparedStatement2.setByte(5, match.getScore().getSet4());
            }
            if (match.getScore().getSet4() == null) {
            	preparedStatement2.setNull(6, Types.TINYINT);
            }
            else {
            	preparedStatement2.setByte(6, match.getScore().getSet5());
            }
           
            preparedStatement2.executeUpdate();
            
            ResultSet rs2 = preparedStatement2.getGeneratedKeys();
            
            if (rs2.next()) {
            	match.getScore().setId(rs2.getLong(1));
            }
            
            System.out.println("Score créé");
            
            /***************/
            conn.commit();
            /***************/
            
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
