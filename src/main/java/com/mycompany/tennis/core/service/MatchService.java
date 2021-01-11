package com.mycompany.tennis.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {
	private ScoreRepositoryImpl scoreRepository;
	private MatchRepositoryImpl matchRepository;
	
	public MatchService() {
		this.scoreRepository = new ScoreRepositoryImpl();
		this.matchRepository = new MatchRepositoryImpl();
		
	}
	
	public void enregistrerNouveauMatch(Match match) {
		matchRepository.create(match);
		scoreRepository.create(match.getScore());
	}
	
	public MatchDto getMatch(Long id) {
		Session session= null;
		Transaction tx = null;
		Match match = null;
		MatchDto matchDto=null;
		EpreuveFullDto epreuveFullDto=null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			match=matchRepository.getById(id);
			//Hibernate.initialize(epreuve.getTournoi());
		    matchDto=new MatchDto();
		    matchDto.setId(match.getId());
		    
			JoueurDto finalisteDto=new JoueurDto();
			finalisteDto.setId(match.getFinaliste().getId());
			finalisteDto.setNom(match.getFinaliste().getNom());
			finalisteDto.setPrenom(match.getFinaliste().getPrenom());
			finalisteDto.setSexe(match.getFinaliste().getSexe());
			matchDto.setFinaliste(finalisteDto);
			
			JoueurDto vainqueurDto=new JoueurDto();
			vainqueurDto.setId(match.getVainqueur().getId());
			vainqueurDto.setNom(match.getVainqueur().getNom());
			vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
			vainqueurDto.setSexe(match.getVainqueur().getSexe());
			matchDto.setVainqueur(vainqueurDto);
			
			epreuveFullDto=new EpreuveFullDto();
			epreuveFullDto.setId(match.getEpreuve().getId());
			epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
			TournoiDto tournoiDto=new TournoiDto();
			tournoiDto.setId(match.getEpreuve().getTournoi().getId());
			tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
			tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
			epreuveFullDto.setTournoi(tournoiDto);
			
			matchDto.setEpreuve(epreuveFullDto);
			
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
		return matchDto;
	}
}
