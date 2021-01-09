package com.mycompany.tennis.core.repository;

import org.hibernate.Session;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;


public class EpreuveRepositoryImpl {

	public Epreuve getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Epreuve epreuve = session.get(Epreuve.class, id);
		System.out.println("Epreuve lu");
		
		return epreuve;
	}
	
	
}
