package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class ScoreService {
	private ScoreRepositoryImpl scoreRepository;

	public ScoreService() {
		this.scoreRepository = new ScoreRepositoryImpl();
	}
	
	public ScoreFullDto getScore(Long id) {
		Session session=null;
		Transaction tx=null;
		Score score=null;
		ScoreFullDto scoreFullDto=null;
		try {
			/*session=HibernateUtil.getSessionFactory().openSession();*/
			/* J'aurais du faire un openSession dans le code précédent */
			session=HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			score =scoreRepository.getById(id);
		    scoreFullDto=new ScoreFullDto();
			scoreFullDto.setId(score.getId());
			scoreFullDto.setSet1(score.getSet1());
			scoreFullDto.setSet2(score.getSet2());
			scoreFullDto.setSet3(score.getSet3());
			scoreFullDto.setSet4(score.getSet4());
			scoreFullDto.setSet5(score.getSet5());
			tx.commit();
		}
		catch (Exception e){
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
		return scoreFullDto;
	}
}
