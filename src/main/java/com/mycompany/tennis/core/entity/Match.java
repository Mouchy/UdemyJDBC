package com.mycompany.tennis.core.entity;

public class Match {
	private Long Id;
	private Joueur vainqueur;
	private Joueur finaliste;
	private Epreuve epreuve;
	private Score   score;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Joueur getVainqueur() {
		return vainqueur;
	}
	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}
	public Joueur getFinaliste() {
		return finaliste;
	}
	public void setFinaliste(Joueur finaliste) {
		this.finaliste = finaliste;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	
	
}
