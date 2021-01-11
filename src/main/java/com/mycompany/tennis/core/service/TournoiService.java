package com.mycompany.tennis.core.service;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;
	  public TournoiService() {
	    this.tournoiRepository = new TournoiRepositoryImpl();
	  }

	  public void createTournoi(TournoiDto tournoiDto) {
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			Tournoi tournoi=new Tournoi();
			tournoi.setId(tournoiDto.getId());
			tournoi.setCode(tournoiDto.getCode());
			tournoi.setNom(tournoiDto.getNom());
			tournoiRepository.create(tournoi);
			tx.commit();
		}
		catch (Exception e) {
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

	  public TournoiDto getTournoi(Long id) {
		Session session=null;
		Transaction tx=null;
		Tournoi tournoi=null;
		TournoiDto tournoiDto=null;
		try {
			session=HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			tournoi=tournoiRepository.getById(id);
			tournoiDto=new TournoiDto();
			tournoiDto.setId(tournoi.getId());
			tournoiDto.setNom(tournoi.getNom());
			tournoiDto.setCode(tournoi.getCode());
			tx.commit();
		}
		catch (Exception e) {
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
		return tournoiDto;
    }
	
	public void deleteTournoi(Long id) {
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			tournoiRepository.delete(id);
			tx.commit();
		}
		catch (Exception e) {
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
}
