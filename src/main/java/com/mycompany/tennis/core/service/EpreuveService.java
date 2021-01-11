package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;


public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
    	this.epreuveRepository=new EpreuveRepositoryImpl();
    }
    
    
    public EpreuveFullDto getEpreuveAvecTournoi(Long id) {
		Session session= null;
		Transaction tx = null;
		Epreuve epreuve = null;
		EpreuveFullDto epreuveFulldto=null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			epreuve=epreuveRepository.getById(id);
			//Hibernate.initialize(epreuve.getTournoi());
			epreuveFulldto=new EpreuveFullDto();
			epreuveFulldto.setId(epreuve.getId());
			epreuveFulldto.setAnnee(epreuve.getAnnee());
			epreuveFulldto.setTypeEpreuve(epreuve.getTypeEpreuve());
			TournoiDto tournoiDto=new TournoiDto();
			tournoiDto.setId(epreuve.getTournoi().getId());
			tournoiDto.setNom(epreuve.getTournoi().getNom());
			tournoiDto.setCode(epreuve.getTournoi().getCode());
			epreuveFulldto.setTournoi(tournoiDto);
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
		return epreuveFulldto;
	}
    
    
    
    
    public EpreuveLightDto getEpreuveSansTournoi(Long id) {
		Session session= null;
		Transaction tx = null;
		Epreuve epreuve = null;
		EpreuveLightDto dto=null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			epreuve=epreuveRepository.getById(id);
			dto=new EpreuveLightDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
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
		return dto;
	}
    
	
	
}
