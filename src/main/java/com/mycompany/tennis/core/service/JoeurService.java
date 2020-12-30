package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class JoeurService {
	private JoueurRepositoryImpl joueurRepository;

	public JoeurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}
	
	public void createJoueur(Joueur joueur) {
		joueurRepository.create(joueur);
	}
}
