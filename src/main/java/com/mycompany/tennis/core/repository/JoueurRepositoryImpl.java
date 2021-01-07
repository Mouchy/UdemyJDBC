package com.mycompany.tennis.core.repository;



import org.hibernate.Session;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;

public class JoueurRepositoryImpl {

	public void renomme(Long id, String nouveauNom) {
		Joueur 		joueur	=null;
		Session 	session	=null;
	
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		joueur = session.get(Joueur.class, id);
		joueur.setNom(nouveauNom);
		System.out.println("Nom du Joueur modifié");
		
	}
	
	public void create(Joueur joueur) {
		Session session=null;
	
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.persist(joueur);
		System.out.println("joueur créé");
    }
	
	
	
	public void delete(Long id) {
		Joueur joueur = new Joueur();
		joueur.setId(id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(joueur);
		System.out.println("Tournoi effacer");
	}
	
	public Joueur getById(Long id) {
		Joueur joueur=null;
		Session session=null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		joueur = session.get(Joueur.class, id);
		System.out.println("Joueur lu");
		
		return joueur;
	}
}
