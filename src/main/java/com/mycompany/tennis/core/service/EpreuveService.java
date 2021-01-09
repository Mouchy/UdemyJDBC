package com.mycompany.tennis.core.service;



import java.util.Scanner;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;


public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
    	this.epreuveRepository=new EpreuveRepositoryImpl();
    }
    
    
    public Epreuve getEpreuveAvecTournoi(Long id) {
		Session session= null;
		Transaction tx = null;
		Epreuve epreuve = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			epreuve=epreuveRepository.getById(id);
			Hibernate.initialize(epreuve.getTournoi());
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
		return epreuve;
	}
    
    
    
    
    public Epreuve getEpreuveSansTournoi(Long id) {
		Session session= null;
		Transaction tx = null;
		Epreuve epreuve = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			epreuve=epreuveRepository.getById(id);
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
		return epreuve;
	}
    
	
	
}
