package com.mycompany.tennis.core.service;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {
	private JoueurRepositoryImpl joueurRepository;

	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}
	
	public void renomme(Long id, String nouveauNom) {
		joueurRepository.renomme(id, nouveauNom);
	}
	
	public void createJoueur(Joueur joueur) {
		joueurRepository.create(joueur);
	}
	
	public Joueur getJoueur(Long id) {
		Session session=null;
		Transaction tx=null;
		Joueur joueur=null;
		try {
			session=HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			joueur=joueurRepository.getById(id);
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
		return joueur;
	}
	
	public void deleteJoueur(Long id) {
		Session session=null;
		Transaction tx=null;
		try {
			session=HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			joueurRepository.delete(id);
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
