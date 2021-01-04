package com.mycompany.tennis.core.service;

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
		System.out.println("Match"+match.getId());
		System.out.println("Score");
		System.out.println("Score - Match"+match.getScore().getMatch().getId());
		scoreRepository.create(match.getScore());
	}
}
